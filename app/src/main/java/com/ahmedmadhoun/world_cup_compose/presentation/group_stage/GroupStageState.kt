package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

import android.location.Location

data class GroupStageState(
    val isScreenLoading: Boolean = false,
    val screenError: String = "",
    val screenSuccess: String = "",
)