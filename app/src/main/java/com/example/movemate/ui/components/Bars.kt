package com.example.movemate.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.movemate.R
import com.example.movemate.data.availableVehicles
import com.example.movemate.data.models.UserProfile
import com.example.movemate.data.sampleShipments
import com.example.movemate.ui.home.BottomNavigationDirections
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.LightGray
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.Purple40
import com.example.movemate.ui.theme.TextColor
import com.example.movemate.ui.theme.TextHintColor
import com.example.movemate.ui.theme.simpleTextStyle
import kotlinx.coroutines.launch


data class BottomBarItem(
    val title: String,
    @DrawableRes val icon: Int,
    val index: Int
)

val defaultTabs = listOf(
    BottomBarItem(
        title = "Home",
        icon = R.drawable.mv_home,
        index = BottomNavigationDirections.Home.index
    ),

    BottomBarItem(
        title = "Calculate",
        icon = R.drawable.mv_calculator,
        index =  BottomNavigationDirections.Calculator.index
    ),

    BottomBarItem(
        title = "Shipment",
        icon = R.drawable.mv_reset_clock,
        index =  BottomNavigationDirections.Shipment.index
    ),

    BottomBarItem(
        title = "Profile",
        icon = R.drawable.mv_user,
        index =  BottomNavigationDirections.Profile.index
    ),
)

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomBarItem> = defaultTabs,
    selectedIndex: Int = BottomNavigationDirections.Home.index,
    onItemSelected: (Int) -> Unit
) {

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(90.dp)
    ) {
        this

        BottomNavIndicator(selectedIndex = selectedIndex, size = items.size)

        // Items Row below the indicator
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp), // Push items down so the slider can sit above
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                AppBottomBarItem(
                    item = item,
                    isSelected = item.index == selectedIndex,
                    onClick = { onItemSelected(index) }
                )
            }
        }
    }
}

@Composable
fun BottomNavIndicator(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    size: Int,
    indicatorColor: Color = Purple40,
    screenWidth: Int = LocalConfiguration.current.screenWidthDp
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember {
        Animatable(selectedIndex * (screenWidth.toFloat() / size))
    }
    DisposableEffect(selectedIndex) {
        val animationJob = coroutineScope.launch {
            offsetX.animateTo(
                targetValue = selectedIndex * (screenWidth.toFloat() / size),
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
        }
        onDispose {
            animationJob.cancel()
        }
    }
    Box(
        Modifier
            .width((screenWidth.toFloat() / size).dp)
            .height(3.dp)
            .offset(x = offsetX.value.dp)
            .background(indicatorColor, CircleShape)
            .then(modifier)
    )
}


@Composable
fun AppBottomBarItem(
    item: BottomBarItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) Purple40 else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(item.icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(30.dp)
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = item.title,
            color = color,
            style = simpleTextStyle.copy(
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400
            )
        )
    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalLayoutApi::class)
@Composable
fun AppHomeTopBar(
    searchValue: String = "",
    onUpdateSearchValue: (String) -> Unit,
    onBack: () -> Unit,
    onSearchFocused: (Boolean) -> Unit,
    searchIsFocused: Boolean = false,
    modifier: Modifier = Modifier
) {
    val ime = WindowInsets.isImeVisible


    LaunchedEffect(ime) {
        if (ime) {
            onSearchFocused(true)
        }
    }

    val boxHeight by animateDpAsState(
        targetValue = if (searchIsFocused) 100.dp else 150.dp,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        label = "box height"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Purple40)
            .height(boxHeight)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Crossfade(
                targetState = !searchIsFocused && searchValue.isEmpty(),
                animationSpec = tween(durationMillis = 200, easing = EaseInOut)
            ) { showProfile ->

                if (showProfile) {
                    ProfileBar()
                }
            }

            TopBarSearch(
                searchIsFocused = searchIsFocused,
                searchValue = searchValue,
                onTextChange = { value ->
                    onUpdateSearchValue(value)
                },
                onBack = {onBack()},
                onSearchFocused = { state ->
                    onSearchFocused(state)
                }
            )
        }

    }
}

@Composable
fun ProfileBar(
    profile: UserProfile = UserProfile.default,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.background(Purple40)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(2f)
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Image(
                painter = painterResource(profile.profilePic),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)

            )

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mv_send),
                        contentDescription = "search",
                        tint = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Your location",
                        style = simpleTextStyle.copy(color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = profile.location,
                        style = simpleTextStyle.copy(color = Color.White)
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "down",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(R.drawable.mv_notification_bell),
                    contentDescription = "notification",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "",
    onBack: () -> Unit,
    modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                title,
                style = simpleTextStyle.copy(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Color.White
        ),
    )
}

@Composable
fun TopBarSearch(
    onBack: () -> Unit,
    searchValue: String = "",
    onTextChange: (String) -> Unit = {},
    onSearchFocused: (Boolean) -> Unit = {},
    searchIsFocused: Boolean = false,
    modifier: Modifier = Modifier
) {

    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        AnimatedVisibility(
            visible = searchIsFocused || searchValue.isNotEmpty()
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "back",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        keyboard?.hide()
                        onBack()
                        onSearchFocused(false)
                    }
            )
        }
        AppTextField(
            modifier = Modifier.onFocusChanged {
                onSearchFocused(it.hasFocus)
            },
            value = searchValue,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.mv_search),
                    contentDescription = "search",
                    tint = TextHintColor,
                    modifier = Modifier.size(20.dp)
                )
            },
            onActionClicked = {
                keyboard?.hide()
            },
            trailingIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(DarkYellow)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mv_scan),
                        contentDescription = "scan",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            placeholder = {
                Text("Enter the receipt number...", style = simpleTextStyle.copy(color = TextHintColor))
            },
            borderColor = Color.Transparent
        ) { value ->
            onTextChange(value)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    MoveMateTheme {
        AppHomeTopBar(
            onSearchFocused = {},
            onUpdateSearchValue = {},
            onBack = {}
        )
    }
}


@Preview
@Composable
private fun BottomBarsPreview() {
    MoveMateTheme {
        var selectedIndex by rememberSaveable { mutableStateOf(0) }
        var searchValue by rememberSaveable { mutableStateOf("") }
        var searchIsFocused by rememberSaveable { mutableStateOf(false) }

        Scaffold(
            topBar = {
                AppHomeTopBar(
                    searchValue = searchValue,
                    searchIsFocused = searchIsFocused,
                    onSearchFocused = { state ->
                        searchIsFocused = state
                    },
                    onUpdateSearchValue = { value -> searchValue = value },
                    onBack = {}
                )
            }

        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                // Your main content goes here

                Column {
                    AnimatedVisibility(
                        visible = !searchIsFocused && searchValue.isEmpty()
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Spacer(Modifier.height(20.dp))

                            ShipmentTrackingCard()

                            Spacer(Modifier.height(20.dp))

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

                    AnimatedVisibility(
                        visible = searchIsFocused || searchValue.isNotEmpty()
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Spacer(Modifier.height(12.dp))
                            ShipmentSearchList(shipments = sampleShipments.filter {
                                it.shipmentNumber.lowercase()
                                    .contains(searchValue) || it.itemName.lowercase()
                                    .contains(searchValue)
                            })
                        }
                    }
                }

                AnimatedVisibility(
                    visible = !searchIsFocused,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(durationMillis = 300)
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    AppBottomBar(
                        selectedIndex = selectedIndex,
                        onItemSelected = { selectedIndex = it }
                    )
                }
            }
        }
    }
}
