package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.data.local.Match

sealed class SemiFinalEvent {

    data class OnSubmit(
        val navController: NavController,
        val finalList: MutableList<Match>,
        val thirdPlaceList: MutableList<Match>
    ) : SemiFinalEvent()

}