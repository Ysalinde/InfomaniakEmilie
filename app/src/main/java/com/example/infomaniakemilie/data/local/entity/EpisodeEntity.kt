package com.example.infomaniakemilie.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * EpisodeEntity - Entity
 * Database structure of an episode of a show
 */
@Entity
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val season: Int,
    val number: Int,
    val runtime: Int?,
    val rating: Double?,
    val mediumImg: String?,
    val largeImg: String?,
    val summary: String?,
)
