package com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals

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
class QuarterFinalViewModel @Inject constructor(
    private val repository: NationalTeamsRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(QuarterFinalState())
        internal set

    val observeData = repository.observeDataJson(2)

    var quarterFinalsList = mutableListOf<Match>()
    var quarterFinalsListLocal = mutableListOf<Match>()


    fun getQuarterFinalsData(dataJson: DataJson) {
        val jsonList = JSONArray(Gson().fromJson(dataJson.jsonList, List::class.java))
        for (i in 0 until jsonList.length()) {
            quarterFinalsListLocal.add(
                Match(
                    team1 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team1").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team1").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team1").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team1").getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team1").getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team1").getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team1").getInt("id"),
                    ),
                    team2 = NationalTeam(
                        name = jsonList.getJSONObject(i).getJSONObject("team2").getString("name"),
                        image = jsonList.getJSONObject(i).getJSONObject("team2").getInt("image"),
                        group = jsonList.getJSONObject(i).getJSONObject("team2").getInt("group"),
                        isQualified = jsonList.getJSONObject(i).getJSONObject("team2").getBoolean("isQualified"),
                        isFirstInGroup = jsonList.getJSONObject(i).getJSONObject("team2").getBoolean("isFirstInGroup"),
                        listType = jsonList.getJSONObject(i).getJSONObject("team2").getInt("listType"),
                        id = jsonList.getJSONObject(i).getJSONObject("team2").getInt("id"),
                    )
                )
            )
        }
        quarterFinalsListLocal.forEach {
            Log.e("RoundOf16Screen", ": ${it}", )
        }
    }

    fun onEvent(event: QuarterFinalEvent) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }
    fun insertQuarterFinalsList(dataJson: DataJson) = viewModelScope.launch {
        repository.insertDataJson(dataJson)
    }


}
