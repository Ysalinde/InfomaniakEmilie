package com.example.infomaniakemilie.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.infomaniakemilie.data.local.ShowDatabase
import com.example.infomaniakemilie.data.local.ShowEntity
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.data.remote.ShowRemoteMediator
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
            remoteMediator = ShowRemoteMediator(
                showDb = showDb,
                mazeApi = mazeApi
            ),
            pagingSourceFactory = {
                showDb.dao.pagingSource()
            }
        )
    }
}