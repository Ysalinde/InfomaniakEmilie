package com.example.infomaniakemilie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infomaniakemilie.data.local.entity.EpisodeDao
import com.example.infomaniakemilie.data.local.entity.EpisodeEntity

@Database(
    entities = [EpisodeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EpisodeDatabase(): RoomDatabase() {

    abstract val dao: EpisodeDao
}