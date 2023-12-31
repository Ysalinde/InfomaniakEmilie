package com.example.infomaniakemilie.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.infomaniakemilie.data.local.EpisodeDatabase
import com.example.infomaniakemilie.data.local.SeasonDatabase
import com.example.infomaniakemilie.data.local.ShowDatabase
import com.example.infomaniakemilie.data.local.entity.ShowEntity
import com.example.infomaniakemilie.data.remote.AllShowsRemoteMediator
import com.example.infomaniakemilie.data.remote.MazeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShowDatabase(@ApplicationContext context: Context) : ShowDatabase{
        return Room.databaseBuilder(
            context,
            ShowDatabase::class.java,
            "shows.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSeasonDatabase(@ApplicationContext context: Context) : SeasonDatabase{
        return Room.databaseBuilder(
            context,
            SeasonDatabase::class.java,
            "seasons.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEpisodeDatabase(@ApplicationContext context: Context) : EpisodeDatabase{
        return Room.databaseBuilder(
            context,
            EpisodeDatabase::class.java,
            "episodes.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShowApi(): MazeApi {
        return Retrofit.Builder()
            .baseUrl(MazeApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideShowPager(showDb: ShowDatabase, mazeApi: MazeApi): Pager<Int, ShowEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = AllShowsRemoteMediator(
                showDb = showDb,
                mazeApi = mazeApi
            ),
            pagingSourceFactory = {
                showDb.dao.pagingSource()
            }
        )
    }

}