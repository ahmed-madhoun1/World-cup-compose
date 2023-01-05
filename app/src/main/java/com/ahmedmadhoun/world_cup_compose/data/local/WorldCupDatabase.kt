package com.ahmedmadhoun.world_cup_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NationalTeam::class],
    version = 2
)
abstract class WorldCupDatabase : RoomDatabase() {

    abstract fun nationalTeamsDao(): NationalTeamsDao

}