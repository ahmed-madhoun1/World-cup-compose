package com.ahmedmadhoun.world_cup_compose.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "national_teams")
data class NationalTeam(
    var name: String,
    var isQualified: Boolean = false,
    var isFirstInGroup: Boolean = false,
    var group: Int,
    var listType: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable
