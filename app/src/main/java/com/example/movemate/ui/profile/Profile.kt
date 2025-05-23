package com.example.movemate.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movemate.ui.components.AppTopBar
import com.example.movemate.ui.theme.simpleTextStyle

@Composable
fun ProfileRoute(
    onBack:  () -> Unit,
    modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AppTopBar(onBack = {}, title = "Profile")
        },
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            Text("Profile", style = simpleTextStyle)
        }
    }
}