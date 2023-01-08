package com.ahmedmadhoun.world_cup_compose.presentation.final_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class FinalViewModel @Inject constructor(
    var repository: NationalTeamsRepository
) : ViewModel() {

    var state by mutableStateOf(FinalState())
        internal set

    val observeData = repository.observeDataJson(4)
    val observeThirdPlaceData = repository.observeDataJson(5)

    var finalsList = mutableListOf<Match>()
    var thirdPlaceList = mutableListOf<Match>()
    var finalsListLocal = mutableListOf<Match>()
    var thirdPlaceListLocal = mutableListOf<Match>()

    lateinit var winner: NationalTeam

    fun getWinner() {
        finalsListLocal.forEach {
            winner = if (it.team1.isQualified) {
                it.team1
            } else {
                it.team2
            }
        }
    }

    fun getFinalsData(dataJson: DataJson) {
        val jsonList = JSONArray(Gson().fromJson(dataJson.jsonList, List::class.java))
        for (i in 0 until jsonList.length()) {
            finalsListLocal.add(
                Match(
                    team1 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team1").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team1").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team1").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team1").getInt("id"),
                    ),
                    team2 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team2").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team2").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team2").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team2").getInt("id"),
                    )
                )
            )
        }
    }

    fun getThirdPlaceData(dataJson: DataJson) {
        val jsonList = JSONArray(Gson().fromJson(dataJson.jsonList, List::class.java))
        for (i in 0 until jsonList.length()) {
            thirdPlaceListLocal.add(
                Match(
                    team1 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team1").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team1").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team1").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team1")
                            .getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team1").getInt("id"),
                    ),
                    team2 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team2").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team2").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team2").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team2")
                            .getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team2").getInt("id"),
                    )
                )
            )
        }
        getWinner()
    }

    fun onEvent(event: FinalEvent) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }

    fun insertFinalsList(dataJson: DataJson) = viewModelScope.launch {
        repository.insertDataJson(dataJson)
    }
}

