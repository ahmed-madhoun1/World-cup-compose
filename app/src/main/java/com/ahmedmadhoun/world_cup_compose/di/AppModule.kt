package com.ahmedmadhoun.world_cup_compose.di

import android.content.Context
import androidx.room.Room
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeamsDao
import com.ahmedmadhoun.world_cup_compose.data.local.WorldCupDatabase
import com.ahmedmadhoun.world_cup_compose.data.repository.NationalTeamsRepositoryImpl
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideWorldCupDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, WorldCupDatabase::class.java, "world_cup_database")
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNationalTeamDao(
        database: WorldCupDatabase
    ) = database.nationalTeamsDao()

    @Provides
    @Singleton
    fun provideNationalTeamsRepository(
        dao: NationalTeamsDao,
    ) = NationalTeamsRepositoryImpl(dao) as NationalTeamsRepository


}
