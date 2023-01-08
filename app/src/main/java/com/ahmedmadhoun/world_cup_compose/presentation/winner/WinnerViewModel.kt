package com.ahmedmadhoun.world_cup_compose.presentation.winner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class WinnerViewModel @Inject constructor(
    private val repository: NationalTeamsRepository,
) : ViewModel() {


    var state by mutableStateOf(WinnerState())
        internal set




}
