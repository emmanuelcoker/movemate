package com.example.movemate.ui.calculate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.data.itemCategories
import com.example.movemate.ui.components.AppButton
import com.example.movemate.ui.components.AppTextField
import com.example.movemate.ui.components.AppTopBar
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.TextColor
import com.example.movemate.ui.theme.TextFieldBackgroundColor
import com.example.movemate.ui.theme.TextHintColor
import com.example.movemate.ui.theme.simpleTextStyle
import kotlinx.coroutines.delay

@Composable
fun CalculatorRoute(
    onBack: () -> Unit,
    onCalculate: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        AppTopBar(onBack = { onBack() }, title = "Calculate")

        CalculatorForm(onCalculate = {onCalculate()})
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorForm(
    onCalculate: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {

        DestinationCard()

        Spacer(Modifier.height(20.dp))

        PackageDetailContent(onCalculate = onCalculate)

    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PackageDetailContent(onCalculate: () -> Unit, modifier: Modifier = Modifier) {
    val keyboard = LocalSoftwareKeyboardController.current

    var sendingItem by remember { mutableStateOf("Box") }
    var category by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300, easing = LinearEasing),
            initialOffsetY = { it/2 }
        )
    ) {
        Column {
            Text(
                text = "Packaging",
                style = simpleTextStyle.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = TextColor
                )
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "What are you sending?",
                style = simpleTextStyle.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = TextHintColor
                )
            )

            Spacer(Modifier.height(20.dp))

            AppTextField(
                modifier = Modifier,
                value = sendingItem,
                maxLines = 1,
                cornerRadius = 10.dp,
                leadingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mv_box_grey),
                            contentDescription = "search",
                            tint = TextHintColor,
                            modifier = Modifier.size(20.dp)
                        )
                        VerticalDivider(
                            thickness = 1.dp, color = TextHintColor,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "search",
                        modifier = Modifier.size(20.dp)
                    )
                },
                onActionClicked = {
                    keyboard?.hide()
                },
                backgroundColor = TextFieldBackgroundColor,
                placeholder = {
                    Text("What are you sending", style = simpleTextStyle.copy(color = TextHintColor))
                },
                borderColor = Color.Transparent
            ) { value ->
                sendingItem = value
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Categories",
                style = simpleTextStyle.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = TextColor
                )
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "What are you sending?",
                style = simpleTextStyle.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = TextHintColor
                )
            )

            Spacer(Modifier.height(20.dp))

            FlowRow(
                maxItemsInEachRow = 5,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                itemCategories.forEachIndexed { index, item ->
                    var itemVisible by remember { mutableStateOf(false) }

                    LaunchedEffect(visible) {
                        if (visible) {
                            delay(index * 50L)
                            itemVisible = true
                        }
                    }

                    SlideInHorizontallyAnimation(
                        state = itemVisible,
                        content = {
                            CategoryItem(
                                itemName = item,
                                isSelected = category == item,
                                onClick = { value -> category = value }
                            )
                        }
                    )
                }
            }


            Spacer(Modifier.height(60.dp))

            AppButton(
                onClick = {onCalculate()},
                title = "Calculate"
            )
        }
    }
}


@Composable
fun CategoryItem(
    itemName: String = "",
    isSelected: Boolean = false,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, color = TextColor, shape = RoundedCornerShape(10.dp))
            .background(if (isSelected) TextColor else Color.White)
            .clickable { onClick(itemName) }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { onClick(itemName) }
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
            }
            Text(
                itemName,
                style = simpleTextStyle.copy(color = if (isSelected) Color.White else TextColor),
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
fun DestinationCard(modifier: Modifier = Modifier) {
    val keyboard = LocalSoftwareKeyboardController.current
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var approxWeight by remember { mutableStateOf("") }

    var visible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 200),
            initialOffsetY = { fullHeight -> fullHeight }
        )
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
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                AppTextField(
                    modifier = Modifier,
                    value = senderLocation,
                    maxLines = 1,
                    cornerRadius = 10.dp,
                    leadingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.mv_box_up),
                                contentDescription = "search",
                                tint = TextHintColor,
                                modifier = Modifier.size(20.dp)
                            )
                            VerticalDivider(
                                thickness = 1.dp, color = TextHintColor,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }

                    },
                    backgroundColor = TextFieldBackgroundColor,
                    placeholder = {
                        Text("Sender Location", style = simpleTextStyle.copy(color = TextHintColor))
                    },
                    onActionClicked = {
                        keyboard?.hide()
                    },
                    borderColor = Color.Transparent
                ) { value ->
                    senderLocation = value
                }

                AppTextField(
                    modifier = Modifier,
                    value = receiverLocation,
                    maxLines = 1,
                    cornerRadius = 10.dp,
                    leadingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.mv_box_down),
                                contentDescription = "search",
                                tint = TextHintColor,
                                modifier = Modifier.size(20.dp)
                            )
                            VerticalDivider(
                                thickness = 1.dp, color = TextHintColor,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }

                    },
                    backgroundColor = TextFieldBackgroundColor,
                    onActionClicked = {
                        keyboard?.hide()
                    },
                    placeholder = {
                        Text("Receiver Location", style = simpleTextStyle.copy(color = TextHintColor))
                    },
                    borderColor = Color.Transparent
                ) { value ->
                    receiverLocation = value
                }


                AppTextField(
                    modifier = Modifier,
                    value = approxWeight,
                    maxLines = 1,
                    cornerRadius = 10.dp,
                    leadingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.mv_weight),
                                contentDescription = "search",
                                tint = TextHintColor,
                                modifier = Modifier.size(20.dp)
                            )
                            VerticalDivider(
                                thickness = 1.dp, color = TextHintColor,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }

                    },
                    backgroundColor = TextFieldBackgroundColor,
                    onActionClicked = {
                        keyboard?.hide()
                    },
                    placeholder = {
                        Text("Approx weight", style = simpleTextStyle.copy(color = TextHintColor))
                    },
                    borderColor = Color.Transparent
                ) { value ->
                    approxWeight = value
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalculatorPreview() {
    MoveMateTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
//            CalculatorRoute(onBack = {}, onCalculate = {})

            CategoryItem(itemName = "Documents", isSelected = true, onClick = {})
        }
    }
}