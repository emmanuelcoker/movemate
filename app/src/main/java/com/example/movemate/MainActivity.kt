package com.example.movemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movemate.ui.MainViewModel
import com.example.movemate.ui.calculate.calculatorGraph
import com.example.movemate.ui.calculate.navigateToCalculatorGraph
import com.example.movemate.ui.components.AppBottomBar
import com.example.movemate.ui.home.BottomNavigationDirections
import com.example.movemate.ui.home.HOME_GRAPH
import com.example.movemate.ui.profile.ProfileRoute
import com.example.movemate.ui.home.homeGraph
import com.example.movemate.ui.home.navigateToHomeGraph
import com.example.movemate.ui.home.navigateWithSingleTop
import com.example.movemate.ui.shipment.navigateToShipmentGraph
import com.example.movemate.ui.shipment.shipmentGraph
import com.example.movemate.ui.theme.MoveMateTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            MoveMateTheme {
                val mainUiState by mainViewModel.uiState.collectAsStateWithLifecycle()

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { testTagsAsResourceId = true },
                    color = MaterialTheme.colorScheme.background
                ) {


                    val navController = rememberNavController()

                    Scaffold(
                        topBar = {
                        }

                    ) { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(padding)
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = HOME_GRAPH,
                            ) {

                                homeGraph(
                                    navController = navController,
                                    mainViewModel = mainViewModel
                                )

                               shipmentGraph(
                                    navController = navController,
                                     mainViewModel = mainViewModel
                                )

                                calculatorGraph(
                                    navController = navController,
                                    mainViewModel = mainViewModel
                                )


                                composable(BottomNavigationDirections.Profile.route) {
                                    ProfileRoute(onBack = {navController.navigateToHomeGraph()})
                                }
                            }

                            AnimatedVisibility(
                                visible = mainUiState.showBottomBar,
                                enter = slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec = tween(durationMillis = 500)
                                ),
                                exit = slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(durationMillis = 500)
                                ),
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                            ) {
                                AppBottomBar(
                                    selectedIndex = mainUiState.bottomBarIndex,
                                    onItemSelected = { screenIndex ->
                                        mainViewModel.updateBottomBarIndex(screenIndex)
                                        when (screenIndex) {
                                            BottomNavigationDirections.Home.index -> {
                                                mainViewModel.updateBottomBarVisibility(true)
                                                navController.navigateToHomeGraph()
                                            }

                                            BottomNavigationDirections.Calculator.index -> {
                                                mainViewModel.updateBottomBarVisibility(false)
                                                navController.navigateToCalculatorGraph()
                                            }

                                            BottomNavigationDirections.Shipment.index -> {
                                                mainViewModel.updateBottomBarVisibility(false)
                                                navController.navigateToShipmentGraph()
                                            }

                                            BottomNavigationDirections.Profile.index -> {
                                                navController.navigateWithSingleTop(
                                                    BottomNavigationDirections.Profile.route
                                                )
                                            }

                                            else -> {
                                                mainViewModel.updateBottomBarVisibility(true)
                                                navController.navigateToHomeGraph()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}