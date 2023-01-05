package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

import android.location.Location

data class SemiFinalState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)