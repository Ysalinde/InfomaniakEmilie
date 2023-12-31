package com.example.infomaniakemilie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * SeasonEntity - Entity
 * Database structure of a Season for a show
 */
@Entity
data class SeasonEntity(
    @PrimaryKey
    val id: Int,
    val number: Int,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val mediumImg: String?,
    val largeImg: String?,
    val summary: String?,
)
