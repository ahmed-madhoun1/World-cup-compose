package com.ahmedmadhoun.world_cup_compose.presentation.round_of_16

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class RoundOf16ViewModel @Inject constructor(
    private val repository: NationalTeamsRepository,
) : ViewModel() {


    var state by mutableStateOf(RoundOf16State())
        internal set

    val observeData = repository.observeDataJson(1)

    var roundOf16List = mutableListOf<Match>()
    var roundOf16ListLocal = mutableListOf<Match>()

    fun getRoundOf16Data(dataJson: DataJson) {
        val jsonList = JSONArray(Gson().fromJson(dataJson.jsonList, List::class.java))
        for (i in 0 until jsonList.length()) {
            roundOf16ListLocal.add(
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
    }


    fun onEvent(event: RoundOf16Event) {
        when (event) {
            RoundOf16Event.OnSubmit -> TODO()
        }
    }


     fun insertRoundOf16List(dataJson: DataJson) = viewModelScope.launch {
        repository.insertDataJson(dataJson)
    }


}
