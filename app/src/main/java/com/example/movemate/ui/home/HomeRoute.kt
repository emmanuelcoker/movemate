package com.example.movemate.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movemate.data.availableVehicles
import com.example.movemate.data.sampleShipments
import com.example.movemate.ui.MainUiState
import com.example.movemate.ui.MainViewModel
import com.example.movemate.ui.components.ShipmentSearchList
import com.example.movemate.ui.components.ShipmentTrackingCard
import com.example.movemate.ui.components.VehicleItem
import com.example.movemate.ui.theme.simpleTextStyle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.example.movemate.ui.calculate.SlideInVerticallyAnimation
import com.example.movemate.ui.components.AppHomeTopBar
import com.example.movemate.ui.components.ProfileBar
import com.example.movemate.ui.theme.MoveMateTheme
import kotlinx.coroutines.delay

@Composable
fun HomeRoute(
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    var showTopBar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        showTopBar = true
    }
    Scaffold(
        topBar = {

            SlideInVerticallyAnimation(
                state = showTopBar,
                duration = 300,
                isDown = true,
                content = {
                    AppHomeTopBar(
                        searchValue = uiState.searchValue,
                        searchIsFocused = uiState.searchIsFocused,
                        onSearchFocused = { state ->
                            mainViewModel.updateSearchFocus(state)
                        },
                        onBack = {
                            mainViewModel.updateSearchFocus(false)
                            mainViewModel.updateBottomBarVisibility(true)
                        },
                        onUpdateSearchValue = { value -> mainViewModel.updateSearchValue(value) }
                    )
                }
            )
        },
    ) {
        HomeScreen(mainUiState = uiState, modifier = Modifier.padding(it))
    }
}


@Composable
fun HomeScreen(
    mainUiState: MainUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        AnimatedVisibility(
            visible = !mainUiState.searchIsFocused && mainUiState.searchValue.isEmpty(),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                ShipmentTrackingCard()

                Spacer(Modifier.height(20.dp))

                AvailableVehicleHome()

            }
        }

        AnimatedVisibility(
            visible = mainUiState.searchIsFocused || mainUiState.searchValue.isNotEmpty(),
            enter = fadeIn(tween(400)) + slideInVertically(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.height(12.dp))
                ShipmentSearchList(shipments = sampleShipments.filter {
                    it.shipmentNumber.lowercase()
                        .contains(mainUiState.searchValue) || it.itemName.lowercase()
                        .contains(mainUiState.searchValue)
                }.take(4))
            }
        }
    }
}

@Composable
fun AvailableVehicleHome(modifier: Modifier = Modifier) {
    var showSection by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(200)
        showSection = true
    }

    Column {

        AnimatedVisibility(
            visible = showSection,
            enter = fadeIn() +
                    slideIn(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),
                        initialOffset = { fullSize ->
                            IntOffset(x = 0, y = fullSize.height / 2)
                        }
                    )// Optional animation for card appearance
        ) {
            Column {
                Text(
                    text = "Available Vehicles",
                    style = simpleTextStyle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )
                )

                Spacer(Modifier.height(20.dp))

                HorizontalPager(
                    pageSize = PageSize.Fixed(160.dp),
                    contentPadding = PaddingValues(4.dp),
                    state = rememberPagerState(pageCount = { availableVehicles.size })
                ) { item ->
                    val page = availableVehicles[item]
                    VehicleItem(
                        vehicle = page
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    MoveMateTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            HomeScreen(mainUiState = MainUiState())
//            ProfileBar()
        }
    }
}