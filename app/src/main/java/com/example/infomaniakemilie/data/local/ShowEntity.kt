package com.example.infomaniakemilie.data.local

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
    val summary: String,
    val image: String,
)