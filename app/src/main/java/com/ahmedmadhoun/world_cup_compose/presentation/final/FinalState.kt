package com.ahmedmadhoun.world_cup_compose.presentation.final

import android.location.Location

data class FinalState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)