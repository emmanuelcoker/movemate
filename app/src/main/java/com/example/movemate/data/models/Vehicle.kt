package com.example.movemate.data.models

import androidx.annotation.DrawableRes
import com.example.movemate.R

enum class VehicleType(val type: String, val description: String, val icon: Int) {
    SHIP("Ocean freight", "International", R.drawable.mv_ship),
    TRUCK("Cargo freight", "Reliable", R.drawable.mv_truck),
    PLANE("Air freight", "International", R.drawable.mv_plane),
}


data class Vehicle(
    val title: String,
    val subtitle: String,
    @DrawableRes val icon: Int,
)
