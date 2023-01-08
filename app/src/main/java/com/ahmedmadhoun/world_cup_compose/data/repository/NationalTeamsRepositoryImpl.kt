package com.ahmedmadhoun.world_cup_compose.data.repository

import androidx.lifecycle.LiveData
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeamsDao
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import javax.inject.Inject

class NationalTeamsRepositoryImpl @Inject constructor(
    private val nationalTeamsDao: NationalTeamsDao,
) : NationalTeamsRepository {

    override suspend fun insertDataJson(dataJson: DataJson) {
        nationalTeamsDao.insertDataJson(dataJson)
    }

    override suspend fun deleteAllDataJson() {
        nationalTeamsDao.deleteAllDataJson()
    }

    override fun observeDataJson(listType: Int): LiveData<DataJson> =
        nationalTeamsDao.observeDataJsonList(listType)


}