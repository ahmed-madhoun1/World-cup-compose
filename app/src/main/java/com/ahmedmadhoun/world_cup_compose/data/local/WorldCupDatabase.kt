package com.ahmedmadhoun.world_cup_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DataJson::class],
    version = 1
)
//@TypeConverters(Converters::class)
abstract class WorldCupDatabase : RoomDatabase() {

    abstract fun nationalTeamsDao(): NationalTeamsDao

}