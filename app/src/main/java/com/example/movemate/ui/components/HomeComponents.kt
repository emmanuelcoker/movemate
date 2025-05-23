package com.example.movemate.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.data.availableVehicles
import com.example.movemate.data.models.Shipment
import com.example.movemate.data.models.Vehicle
import com.example.movemate.data.sampleShipments
import com.example.movemate.ui.theme.DarkSuccess
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.LightGray
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.TextColor
import com.example.movemate.ui.theme.TextHintColor
import com.example.movemate.ui.theme.simpleTextStyle
import kotlinx.coroutines.delay


@Composable
fun VehicleItem(
    vehicle: Vehicle = availableVehicles.first(),
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }
    var animVisible by remember { mutableStateOf(false) }

    // Trigger animation when the item enters composition
    LaunchedEffect(Unit) {
        visible = true
        delay(200)
        animVisible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(), // Optional animation for card appearance
        modifier = modifier
            .size(150.dp)
    ) {
        ElevatedCard(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Transparent,
            )
        ) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxSize()
            ) {
                // Animate the text sliding up
                Column {
                    AnimatedVisibility(
                        visible = animVisible,
                        enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(
                            tween(
                                durationMillis = 500
                            )
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                        ) {
                            Text(
                                text = vehicle.title,
                                style = simpleTextStyle.copy(fontSize = 16.sp, color = TextColor)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = vehicle.subtitle,
                                style = simpleTextStyle.copy(fontSize = 10.sp, color = TextHintColor)
                            )
                        }
                    }
                }

                Column {
                    // Animate the image sliding in from bottom-end
                    AnimatedVisibility(
                        visible = animVisible,
                        enter = slideIn(
                            initialOffset = { fullSize ->
                                IntOffset(x = fullSize.width / 2, y = -(fullSize.height / 4))
                            }
                        ) + fadeIn(),
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomEnd,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(vehicle.icon),
                                contentDescription = "vehicle${vehicle.title}",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(130.dp).offset(y = 15.dp)
                            )
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun ShipmentTrackingCard(modifier: Modifier = Modifier) {

    var showShipmentCard by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        showShipmentCard = true
    }

    AnimatedVisibility(
        visible = showShipmentCard,
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
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = "Tracking",
                style = simpleTextStyle.copy(fontSize = 16.sp, fontWeight = FontWeight.W600)
            )

            Spacer(Modifier.height(20.dp))

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
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Shipment Number",
                                style = simpleTextStyle.copy(
                                    color = TextHintColor,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.W400
                                )
                            )
                            Text(
                                text = "NEJ20089934122231",
                                style = simpleTextStyle.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600
                                )
                            )
                        }

                        Image(
                            painter = painterResource(R.drawable.mv_forklift),
                            contentDescription = "fork lift icon",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(65.dp)
                                .height(50.dp)
                        )
                    }//title section ends
                    Spacer(Modifier.height(20.dp))

                    HorizontalDivider(thickness = 1.dp, color = LightGray)

                    Spacer(Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        TrackingCardItem(
                            icon = R.drawable.mv_giftbox2,
                            hasIcon = true,
                            title = "Sender",
                            subTitle = {
                                Text(
                                    text = "Atlanta, 5234",
                                    style = simpleTextStyle.copy(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W400
                                    )
                                )
                            },
                            modifier = Modifier.weight(2f)
                        )


                        TrackingCardItem(
                            hasIcon = false,
                            title = "Time",
                            subTitle = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .clip(CircleShape)
                                            .background(DarkSuccess)
                                    )
                                    Text(
                                        text = "2 day - 3 days",
                                        style = simpleTextStyle.copy(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    )
                                }
                            },
                            modifier = Modifier.weight(1.3f)
                        )
                    }

                    Spacer(Modifier.height(30.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TrackingCardItem(
                            icon = R.drawable.mv_giftbox1,
                            hasIcon = true,
                            title = "Receiver",
                            subTitle = {
                                Text(
                                    text = "Chicago, 6342",
                                    style = simpleTextStyle.copy(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W400
                                    )
                                )
                            },
                            modifier = Modifier.weight(2f)
                        )

                        TrackingCardItem(
                            hasIcon = false,
                            title = "Status",
                            subTitle = {
                                Text(
                                    text = "Waiting to collect",
                                    style = simpleTextStyle.copy(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W400
                                    )
                                )
                            },
                            modifier = Modifier.weight(1.3f)
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    HorizontalDivider(thickness = 1.dp, color = LightGray)

                    Row(
                        modifier = Modifier.fillMaxWidth()//.padding(vertical = 20.dp)
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add stop",
                                    tint = DarkYellow,
                                    modifier = Modifier.size(20.dp)
                                )

                                Spacer(Modifier.width(2.dp))

                                Text(
                                    text = "Add Stop",
                                    style = simpleTextStyle.copy(
                                        color = DarkYellow,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.W400
                                    )
                                )
                            }
                        }
                    }

                }
            }
        }
    }


}

@Composable
fun TrackingCardItem(
    @DrawableRes icon: Int? = null,
    hasIcon: Boolean = false,
    title: String,
    subTitle: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        if (hasIcon) {
            if (icon != null) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "item",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Column {
            Text(
                text = title,
                style = simpleTextStyle.copy(
                    color = TextHintColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400
                )
            )
            subTitle()
        }
    }
}

@Composable
fun ShipmentSearchList(
    shipments: List<Shipment> = emptyList(),
    modifier: Modifier = Modifier
) {
    val visibleItems = remember { mutableStateListOf<Boolean>() }
    var showCard by remember { mutableStateOf(false) }

    LaunchedEffect(shipments) {
        if (shipments.isNotEmpty()) {
            showCard = false
            visibleItems.clear()
            repeat(shipments.size) { visibleItems.add(false) }

            // First show the card
            showCard = true

            // Then animate items one by one
            shipments.forEachIndexed { index, _ ->
                if(index < 5) {
                    delay(100 * index.toLong())
                }
                visibleItems[index] = true
            }
        } else {
            showCard = false
        }
    }

    Crossfade(
        targetState = showCard,
        animationSpec = tween(durationMillis = 200, easing = EaseInOut),
    ) { showState ->
        if(showState) {
            ElevatedCard(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight().padding(bottom = 40.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    itemsIndexed(shipments, key = { _, data -> data.shipmentNumber }) { index, item ->
                        AnimatedVisibility(
                            visible = visibleItems.getOrNull(index) == true,
                            enter = slideInVertically(
                                animationSpec = tween(durationMillis = 300),
                                initialOffsetY = { it / 2 }
                            )
                        ) {
                            Column {
                                ShipmentSearchItem(shipment = item)
                                if (index != (shipments.size - 1)) {
                                    Spacer(Modifier.height(12.dp))
                                    HorizontalDivider(thickness = 1.dp, color = LightGray)
                                    Spacer(Modifier.height(12.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShipmentSearchItem(
    shipment: Shipment,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(12.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.mv_box),
            contentDescription = "box",
            modifier = Modifier.size(20.dp)
        )
        Column {
            Text(
                text = shipment.itemName,
                style = simpleTextStyle.copy(
                    color = TextColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Text(
                text = "${shipment.shipmentNumber} ${shipment.sentFrom} -> ${shipment.sentTo}",
                style = simpleTextStyle.copy(
                    color = TextHintColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VehiclePreview() {
    MoveMateTheme {
//        VehicleItem()

//        ShipmentTrackingCard()
//        ShipmentSearchItem(
//            sampleShipments.first()
//        )
        Box(modifier = Modifier.fillMaxSize()) {
            ShipmentSearchList(
                shipments = sampleShipments
            )
        }
    }
}