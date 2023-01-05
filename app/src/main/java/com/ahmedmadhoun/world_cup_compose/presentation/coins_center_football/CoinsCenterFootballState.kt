package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football

import android.location.Location

data class CoinsCenterFootballState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)