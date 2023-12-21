package com.example.infomaniakemilie.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ShowDao {

    @Upsert
    suspend fun upsertAll(shows: List<ShowEntity>)

    @Query("SELECT * FROM ShowEntity")
    fun pagingSource(): PagingSource<Int, ShowEntity>

    @Query("DELETE FROM showentity")
    suspend fun clearAll()
}