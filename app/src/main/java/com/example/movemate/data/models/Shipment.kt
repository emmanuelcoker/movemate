package com.example.movemate.data.models

import androidx.compose.ui.graphics.Color
import com.example.movemate.R
import com.example.movemate.ui.theme.DarkYellow

enum class ShipmentStatus(val title: String, val description: String, val icon: Int, val color: Color) {
    InProgress("In Progress", "in-progress", R.drawable.mv_refresh, Color(0xFF1A831D)),
    Pending("Pending", "Waiting to collect", R.drawable.mv_reset_clock, DarkYellow),
    Cancelled("Cancelled", "cancelled", R.drawable.mv_error_check, Color(0xFFD51010)),
    Completed("Completed", "completed", R.drawable.mv_success_check, Color(0xFF6638B7)),
    Loading("loading", "loading", R.drawable.mv_clock, Color(0xFF2C819D)),
}

data class Shipment(
    val sentFrom: String,
    val sentTo: String,
    val duration: String,
    val status: ShipmentStatus,
    val shipmentNumber: String,
    val amount: Double = 0.0,
    val vehicleType: VehicleType,
    val itemName: String
)
