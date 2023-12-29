package com.example.infomaniakemilie.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ShowDao {

    @Upsert
    suspend fun upsertAll(shows: List<ShowEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(show: ShowEntity)

    @Query("SELECT * FROM ShowEntity ORDER BY name ASC")
    fun pagingSource(): PagingSource<Int, ShowEntity>

    @Query("DELETE FROM showentity")
    suspend fun clearAll()

}