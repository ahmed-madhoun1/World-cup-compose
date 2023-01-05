package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam

sealed class GroupStageEvent {



    data class OnCheckboxChanged(
        val isChecked: Boolean,
        val itemSelected: NationalTeam,
    ) : GroupStageEvent()


    data class OnSubmit(
        val navController: NavController
    ) : GroupStageEvent()

}