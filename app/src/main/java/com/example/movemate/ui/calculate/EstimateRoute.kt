package com.example.movemate.ui.calculate

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.components.AppButton
import com.example.movemate.ui.theme.DarkSuccess
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.Purple40
import com.example.movemate.ui.theme.TextHintColor
import com.example.movemate.ui.theme.simpleTextStyle
import kotlinx.coroutines.delay

@Composable
fun EstimateRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier) {
    val targetAmount = 1460
    var startAnimation by remember { mutableStateOf(false) }
    var boxVisibility by rememberSaveable { mutableStateOf(false) }
    var estimateValueVisibility by rememberSaveable { mutableStateOf(false) }
    var buttonVisibility by rememberSaveable { mutableStateOf(false) }


    BackHandler {
        onBack()
    }

    val animatedAmount by animateIntAsState(
        targetValue = if (startAnimation) targetAmount else 0,
        animationSpec = tween(durationMillis = 2000),
        label = "countUp"
    )

    LaunchedEffect(Unit) {
        delay(100)
        startAnimation = true
        delay(100)
        estimateValueVisibility = true
        delay(100)
        buttonVisibility = true
        boxVisibility = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        ExpandIntoViewAnimation(
            state = startAnimation,
            content = {
                SlideInVerticallyAnimation(
                    state = startAnimation,
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "MoveMate",
                                style = simpleTextStyle.copy(
                                    fontSize = 32.sp,
                                    color = Purple40,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.W600
                                )
                            )
                            Icon(
                                painter = painterResource(R.drawable.mv_fast_truck),
                                contentDescription = "truck icon",
                                tint = DarkYellow,
                                modifier = Modifier.width(70.dp)
                            )
                        }
                    }
                )
            }
        )



        Spacer(Modifier.height(50.dp))

        ExpandIntoViewAnimation(
            state = boxVisibility,
            content = {
                SlideInVerticallyAnimation(
                    state = boxVisibility,
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.mv_box_grey),
                            contentDescription = "box",
                            tint = TextHintColor,
                            modifier = Modifier.size(150.dp)
                        )
                    }
                )
            }
        )

        Spacer(Modifier.height(40.dp))
        ExpandIntoViewAnimation(
            state = estimateValueVisibility,
            content = {
                Text(
                    "Total Estimated Amount",
                    style = simpleTextStyle.copy(fontSize = 26.sp, fontWeight = FontWeight.W400)
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "$$animatedAmount ",
                        style = simpleTextStyle.copy(
                            fontSize = 28.sp,
                            color = DarkSuccess,
                            fontWeight = FontWeight.W400
                        )
                    )
                    Text(
                        "USD",
                        style = simpleTextStyle.copy(
                            fontSize = 20.sp,
                            color = DarkSuccess,
                            fontWeight = FontWeight.W400
                        )
                    )
                }


                Spacer(Modifier.height(16.dp))

                Text(
                    "This amount is estimated. This will vary \nif you change your location and weight",
                    style = simpleTextStyle.copy(
                        fontSize = 16.sp,
                        color = TextHintColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W400
                    )
                )

            }
        )


        SlideInVerticallyAnimation(
            state = buttonVisibility,
            content = {
                Spacer(Modifier.height(40.dp))

                AppButton(
                    onClick = {onBack()},
                    title = "Back Home"
                )
            }
        )

    }
}


@Composable
fun ExpandIntoViewAnimation(
    state: Boolean,
    duration: Int = 200,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state,
        enter = expandIn(
            animationSpec = tween(durationMillis = 300),
            expandFrom = Alignment.TopCenter
        ) + slideInVertically(
            animationSpec = tween(durationMillis = duration),
            initialOffsetY = { it / 2 }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            content()
        }
    }
}


@Composable
fun SlideInVerticallyAnimation(
    state: Boolean,
    duration: Int = 200,
    isDown: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state,
        enter = fadeIn() + slideInVertically(
            animationSpec = tween(duration),
            initialOffsetY = { if(isDown) -it else it }
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            content()
        }
    }
}

@Composable
fun SlideInHorizontallyAnimation(
    state: Boolean,
    duration: Int = 200,
    isLeft: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state,
        enter = fadeIn() + slideInHorizontally(
            animationSpec = tween(durationMillis = duration),
            initialOffsetX = { if (isLeft) -it else it }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            content()
        }
    }
}




@Preview(showBackground = true)
@Composable
private fun EstimatePreview() {
    MoveMateTheme {
        EstimateRoute(onBack = {})
    }
}