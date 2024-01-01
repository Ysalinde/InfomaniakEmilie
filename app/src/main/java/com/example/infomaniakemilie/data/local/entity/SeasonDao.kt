package com.example.infomaniakemilie.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

/**
 * DAO for Database of Seasons
 */
@Dao
interface SeasonDao {

    @Upsert
    suspend fun upsertAll(shows: List<SeasonEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(season: SeasonEntity)

    @Query("DELETE FROM seasonentity")
    suspend fun clearAll()

}
