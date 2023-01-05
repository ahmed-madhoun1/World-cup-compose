package com.ahmedmadhoun.world_cup_compose.domain.repository

import androidx.lifecycle.LiveData
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam

interface NationalTeamsRepository {

    suspend fun insertNationalTeam(nationalTeam: NationalTeam)

    suspend fun deleteNationalTeam(nationalTeam: NationalTeam)

    suspend fun deleteAllNationalTeams()

    fun observeAllNationalTeams(listType: Int): LiveData<List<NationalTeam>>


}