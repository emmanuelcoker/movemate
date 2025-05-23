package com.example.movemate.ui.home

import android.app.Activity
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movemate.ui.MainViewModel

const val HOME_GRAPH = "auth_graph"
fun NavController.navigateToHomeGraph() = navigateWithSingleTop(HOME_GRAPH)

fun NavController.navigateWithSingleTop(
    route: String,
    navOptions: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
) = navigate(route, navOptions)

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    navigation(startDestination = BottomNavigationDirections.Home.route, route = HOME_GRAPH) {

        composable(BottomNavigationDirections.Home.route) {
            HomeRoute(mainViewModel = mainViewModel)
        }
    }

}