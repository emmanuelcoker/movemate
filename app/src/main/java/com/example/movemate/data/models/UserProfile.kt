package com.example.movemate.data.models

import androidx.annotation.DrawableRes
import com.example.movemate.R

data class UserProfile(
    val location: String,
    @DrawableRes val profilePic: Int
) {

    companion object {
        val default = UserProfile(
            location = "Wertheimer, Illinois",
            profilePic = R.drawable.mv_profile
        )
    }
}