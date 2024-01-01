package com.example.infomaniakemilie.data.local.entity

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

/**
 * DAO for Database of Shows
 */
@Dao
interface ShowDao {

    @Upsert
    suspend fun upsertAll(shows: List<ShowEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(show: ShowEntity)

    @Query("SELECT * FROM ShowEntity")
    fun pagingSource(): PagingSource<Int, ShowEntity>

    @Query("DELETE FROM showentity")
    suspend fun clearAll()

}