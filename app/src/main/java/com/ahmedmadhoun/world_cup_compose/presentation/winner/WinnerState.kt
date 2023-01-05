package com.ahmedmadhoun.world_cup_compose.presentation.winner

import android.location.Location

data class WinnerState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)