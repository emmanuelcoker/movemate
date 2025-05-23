package com.example.movemate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.LightGray
import com.example.movemate.ui.theme.MoveMateTheme
import com.example.movemate.ui.theme.TextColor
import com.example.movemate.ui.theme.TextFieldBackgroundColor
import com.example.movemate.ui.theme.TextHintColor

@Composable
fun AppTextField(
    value: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    maxLength: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    onActionClicked: () -> Unit = {},
    borderColor: Color = Color.DarkGray,
    cornerRadius: Dp = 30.dp,
    backgroundColor: Color = Color.White,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(cornerRadius),
                color = borderColor
            )
            .fillMaxWidth()
            .height(50.dp), // Ensure vertical centering
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    onTextValueChange(it)
                }
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Start
            ),
            placeholder = placeholder,
            singleLine = true,
            maxLines = maxLines,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { onActionClicked() },
                onDone = { onActionClicked() }
            ),
            visualTransformation = visualTransformation,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.DarkGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    MoveMateTheme {
        AppTextField(
            value = "Welcome",
            maxLines = 1,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.mv_search),
                    contentDescription = "search",
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFFF5722))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.mv_scan),
                        contentDescription = "scan",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        ) { }
    }
}