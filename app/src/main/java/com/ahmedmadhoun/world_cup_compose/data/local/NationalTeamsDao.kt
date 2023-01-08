package com.ahmedmadhoun.world_cup_compose.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NationalTeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataJson(dataJson: DataJson)

    @Query("SELECT * FROM data_json_list WHERE id == :listType")
    fun observeDataJsonList(listType: Int): LiveData<DataJson>

    @Query("DELETE FROM data_json_list")
    suspend fun deleteAllDataJson()


}