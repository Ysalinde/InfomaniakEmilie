package com.example.infomaniakemilie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShowDatabase(): RoomDatabase() {

    abstract val dao: ShowDao
}
