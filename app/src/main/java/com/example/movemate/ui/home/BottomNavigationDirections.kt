package com.example.movemate.ui.home

sealed class BottomNavigationDirections(val route: String, val index: Int) {
    object Home: BottomNavigationDirections("Home", 0)
    object Calculator: BottomNavigationDirections("Calculator", 1)
    object Shipment: BottomNavigationDirections("Shipment", 2)
    object Profile: BottomNavigationDirections("Profile", 3)
}