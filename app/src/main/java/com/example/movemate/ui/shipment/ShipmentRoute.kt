package com.example.movemate.ui.shipment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.data.models.Shipment
import com.example.movemate.data.models.ShipmentStatus
import com.example.movemate.data.sampleShipments
import com.example.movemate.ui.MainViewModel
import com.example.movemate.ui.components.AppTopBar
import com.example.movemate.ui.components.BottomNavIndicator
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.Purple40
import com.example.movemate.ui.theme.TextColor
import com.example.movemate.ui.theme.TextFieldBackgroundColor
import com.example.movemate.ui.theme.TextHintColor
import com.example.movemate.ui.theme.simpleTextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShipmentRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf("All") }
    var selectedIndex by remember { mutableStateOf(0) }

    var filteredShipments by remember { mutableStateOf(sampleShipments) }

    val visibleMap = remember { mutableStateMapOf<String, Boolean>() }
    var showCard by remember { mutableStateOf(false) }

    // Animate filtered items
    LaunchedEffect(filteredShipments.size) {
        showCard = false
        visibleMap.clear()

        if (filteredShipments.isNotEmpty()) {
            showCard = true
            filteredShipments.forEach {
                visibleMap[it.shipmentNumber] = false
            }

            filteredShipments.forEachIndexed { index, item ->
                delay(50L * index)
                visibleMap[item.shipmentNumber] = true
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ShipmentHistoryTopBar(
            selectedIndex = selectedIndex,
            onBack = { onBack() },
            onTabSelected = { tab, index ->
                selectedTab = tab
                selectedIndex = index
                filteredShipments = sampleShipments.filter {
                    it.status.title.equals(tab, ignoreCase = true) || tab.equals("all", ignoreCase = true)
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))


        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                Text(
                    text = "Shipments",
                    style = simpleTextStyle.copy(fontSize = 16.sp, fontWeight = FontWeight.W600)
                )
            }
            items(
                items = filteredShipments,
                key = { it.shipmentNumber }
            ) { shipment ->
                val isVisible = visibleMap.getOrDefault(shipment.shipmentNumber, false)
                AnimatedVisibility(
                    visible = isVisible,
                    enter = slideInVertically(
                        animationSpec = tween(300),
                        initialOffsetY = { it / 2 }
                    ),
                ) {
                    ShipmentItemCard(shipment = shipment)
                }
            }
        }
    }
}


@Composable
fun ShipmentItemCard(
    shipment: Shipment,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            TextFieldBackgroundColor
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(shipment.status.icon),
                            tint = shipment.status.color,
                            contentDescription = "icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(shipment.status.title, style = simpleTextStyle.copy(color = shipment.status.color))
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    "Arriving today!",
                    style = simpleTextStyle.copy(fontWeight = FontWeight.W600, fontSize = 16.sp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "Your delivery #${shipment.shipmentNumber}, from ${shipment.sentFrom}, is arriving today",
                    style = simpleTextStyle.copy(
                        color = TextHintColor,
                        fontWeight = FontWeight.W400
                    ),
                    modifier = Modifier.width(250.dp)
                )

                Spacer(Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        "$${shipment.amount} USD",
                        style = simpleTextStyle.copy(
                            color = Purple40,
                            fontWeight = FontWeight.W600,
                            fontSize = 14.sp
                        )
                    )
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(TextHintColor)
                    )
                    Text(
                        "Sep 20, 2023",
                        style = simpleTextStyle.copy(
                            color = TextColor,
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp
                        )
                    )

                }
            }

            Image(
                painter = painterResource(R.drawable.mv_box_grey),
                contentDescription = "box",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .offset(x = -10.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentHistoryTopBar(
    selectedIndex: Int,
    onBack: () -> Unit,
    onTabSelected: (String, Int) -> Unit
) {
    val tabs = listOf(
        "All" to sampleShipments.size,
        ShipmentStatus.Completed.title to sampleShipments.filter { it.status.title.lowercase() == ShipmentStatus.Completed.title.lowercase()}.size,
        ShipmentStatus.InProgress.title to sampleShipments.filter { it.status.title.lowercase() == ShipmentStatus.InProgress.title.lowercase()}.size,
        ShipmentStatus.Pending.title to sampleShipments.filter { it.status.title.lowercase() == ShipmentStatus.Pending.title.lowercase()}.size,
        ShipmentStatus.Cancelled.title to sampleShipments.filter { it.status.title.lowercase() == ShipmentStatus.Cancelled.title.lowercase()}.size
    )

    Box(modifier = Modifier.background(Purple40)) {
        Column {
          AppTopBar(
              onBack = onBack,
              title = "Shipment history"
          )

            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                containerColor = Purple40,
                contentColor = Color.White,
                edgePadding = 8.dp,
                divider = {},
                indicator = { tabPositions ->
                    val currentTabPosition = tabPositions[selectedIndex]

                    val offsetX = remember { Animatable(currentTabPosition.left.value) }

                    LaunchedEffect(selectedIndex) {
                        offsetX.animateTo(
                            targetValue = currentTabPosition.left.value,
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }

                    Box(
                        Modifier
                            .wrapContentSize(Alignment.BottomStart)
                            .offset(x = offsetX.value.dp)
                            .width(currentTabPosition.width)
                            .height(2.dp)
                            .background(DarkYellow, RoundedCornerShape(1.dp))
                    )
                }
            ) {
                tabs.forEachIndexed { index, (label, count) ->
                    val isSelected = selectedIndex == index

                    Tab(
                        selected = isSelected,
                        onClick = { onTabSelected(label, index) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(alpha = 0.6f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text(
                                label,
                                style = simpleTextStyle.copy(
                                    fontSize = 14.sp,
                                    color = if (isSelected) Color.White else Color.White.copy(alpha = 0.7f)
                                )
                            )
                            if (count > 0) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .background(
                                            if (isSelected) DarkYellow else TextHintColor.copy(alpha = 0.3f),
                                            shape = RoundedCornerShape(20.dp)
                                        ).height(20.dp)
                                ) {
                                    Text(
                                        text = count.toString(),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.W400,
                                        textAlign = TextAlign.Center,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ShipmentPreview() {
    MoveMateTheme {
        Box(modifier = Modifier.fillMaxSize()) {
//            ShipmentItemCard()

//            var selectedTab by remember { mutableStateOf("All") }
//            var selectedIndex by remember { mutableStateOf(0) }
//
//            ShipmentHistoryTopBar(
//                selectedIndex = selectedIndex,
//                selectedTab = selectedTab,
//                onTabSelected = { tab, index ->
//                    selectedTab = tab
//                    selectedIndex = index
//                }
//            )

            ShipmentRoute(onBack = {})

        }
    }
}