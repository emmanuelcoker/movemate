package com.example.movemate.ui.shipment

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movemate.ui.MainViewModel
import com.example.movemate.ui.home.BottomNavigationDirections
import com.example.movemate.ui.home.navigateToHomeGraph
import com.example.movemate.ui.home.navigateWithSingleTop

const val SHIPMENT_GRAPH = "shipment_graph"
fun NavController.navigateToShipmentGraph() = navigateWithSingleTop(SHIPMENT_GRAPH)


fun NavGraphBuilder.shipmentGraph(
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    navigation(startDestination = BottomNavigationDirections.Shipment.route, route = SHIPMENT_GRAPH) {

        composable(BottomNavigationDirections.Shipment.route) {
            mainViewModel.updateBottomBarVisibility(false)
            ShipmentRoute(onBack = {
                mainViewModel.updateBottomBarIndex(BottomNavigationDirections.Home.index)
                navController.navigateToHomeGraph()
            })
        }
    }
}