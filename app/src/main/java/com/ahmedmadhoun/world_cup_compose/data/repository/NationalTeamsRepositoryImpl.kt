package com.ahmedmadhoun.world_cup_compose.data.repository

import androidx.lifecycle.LiveData
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeamsDao
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import javax.inject.Inject

class NationalTeamsRepositoryImpl @Inject constructor(
    val nationalTeamsDao: NationalTeamsDao,
) : NationalTeamsRepository {

    override suspend fun insertNationalTeam(nationalTeam: NationalTeam) {
        nationalTeamsDao.insertNationalTeam(nationalTeam)
    }

    override suspend fun deleteNationalTeam(nationalTeam: NationalTeam) {
        nationalTeamsDao.deleteNationalTeam(nationalTeam)
    }

    override suspend fun deleteAllNationalTeams() {
        nationalTeamsDao.deleteAllNationalTeams()
    }


    override fun observeAllNationalTeams(listType: Int): LiveData<List<NationalTeam>> =
        nationalTeamsDao.observeAllNationalTeams(listType)

}