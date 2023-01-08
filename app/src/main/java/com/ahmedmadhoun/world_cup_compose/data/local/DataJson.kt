package com.ahmedmadhoun.world_cup_compose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_json_list")
data class DataJson(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val jsonList: String
)
