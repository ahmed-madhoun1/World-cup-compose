package com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals

import android.location.Location

data class QuarterFinalState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val userLocation: Location? = null,
)