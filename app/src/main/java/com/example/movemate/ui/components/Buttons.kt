package com.example.movemate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.ui.theme.DarkYellow
import com.example.movemate.ui.theme.simpleTextStyle

@Composable
fun AppButton(
    onClick: () -> Unit,
    title: String = "",
    buttonColor: Color = DarkYellow,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        Button(
            onClick = {onClick()},
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = textColor
            ),
            modifier = modifier.width(400.dp)
        ) {
            Text(title, style = simpleTextStyle.copy(color = textColor, fontSize = 16.sp), modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}