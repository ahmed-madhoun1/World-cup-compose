package com.ahmedmadhoun.world_cup_compose.presentation.round_of_16

import android.location.Location

data class RoundOf16State(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)