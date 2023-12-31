package com.example.infomaniakemilie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infomaniakemilie.data.local.entity.SeasonDao
import com.example.infomaniakemilie.data.local.entity.SeasonEntity

@Database(
    entities = [SeasonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SeasonDatabase(): RoomDatabase() {

    abstract val dao: SeasonDao
}