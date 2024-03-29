package com.example.infomaniakemilie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * ShowEntity - Entity
 * Database structure of a Show
 */
@Entity
data class ShowEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val mediumImg: String?,
    val largeImg: String?,
    val rating: Double?,
    val averageRuntime: Int?,
    val premiered: String?
)