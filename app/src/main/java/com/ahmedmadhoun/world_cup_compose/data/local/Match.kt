package com.ahmedmadhoun.world_cup_compose.data.local

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "matches")
data class Match(
    var team1Result: Int = 0,
    var team2Result: Int = 0,
    var team1: NationalTeam,
    var team2: NationalTeam
) : Parcelable

@Parcelize
data class ListArgument(
    var list: List<Match>
): Parcelable