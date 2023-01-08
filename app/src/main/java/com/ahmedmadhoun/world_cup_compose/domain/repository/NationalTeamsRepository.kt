package com.ahmedmadhoun.world_cup_compose.domain.repository

import androidx.lifecycle.LiveData
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam

interface NationalTeamsRepository {

    suspend fun  insertDataJson(dataJson: DataJson)

//    suspend fun deleteDataJson(dataJson: DataJson)
//
    suspend fun deleteAllDataJson()

    fun observeDataJson(listType: Int): LiveData<DataJson>
}