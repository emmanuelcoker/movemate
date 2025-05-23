package com.example.movemate.ui.calculate

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.movemate.ui.MainViewModel
import com.example.movemate.ui.home.BottomNavigationDirections
import com.example.movemate.ui.home.navigateToHomeGraph
import com.example.movemate.ui.home.navigateWithSingleTop

const val CALCULATE_GRAPH = "calculator_graph"
fun NavController.navigateToCalculatorGraph() = navigateWithSingleTop(CALCULATE_GRAPH)


const val ESTIMATE_ROUTE = "Estimate_route"

fun NavGraphBuilder.calculatorGraph(
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    navigation(startDestination = BottomNavigationDirections.Calculator.route, route = CALCULATE_GRAPH) {

        composable(BottomNavigationDirections.Calculator.route) {
            mainViewModel.updateBottomBarVisibility(false)
            CalculatorRoute(onBack = {
                mainViewModel.updateBottomBarIndex(BottomNavigationDirections.Home.index)
                navController.navigateToHomeGraph()
            }, onCalculate = {
                navController.navigateWithSingleTop(ESTIMATE_ROUTE)
            })
        }

        composable(ESTIMATE_ROUTE) {
            EstimateRoute(
                onBack = {
                    mainViewModel.updateBottomBarIndex(BottomNavigationDirections.Home.index)
                    mainViewModel.updateBottomBarVisibility(true)
                    navController.navigateToHomeGraph()
                }
            )
        }
    }

}