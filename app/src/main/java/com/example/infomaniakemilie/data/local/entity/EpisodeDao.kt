package com.example.infomaniakemilie.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface EpisodeDao {

    @Upsert
    suspend fun upsertAll(shows: List<EpisodeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(episodeEntity: EpisodeEntity)

    @Query("DELETE FROM episodeentity")
    suspend fun clearAll()
}