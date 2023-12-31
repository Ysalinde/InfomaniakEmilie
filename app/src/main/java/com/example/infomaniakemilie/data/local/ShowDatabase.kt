package com.example.infomaniakemilie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infomaniakemilie.data.local.entity.ShowDao
import com.example.infomaniakemilie.data.local.entity.ShowEntity

@Database(
    entities = [ShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShowDatabase(): RoomDatabase() {

    abstract val dao: ShowDao
}
