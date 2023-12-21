package com.example.infomaniakemilie.data.remote

/*
 * Show - Data to object
 * Coming from JSON request
 */
data class ShowDto(
    val id: Int,
    val name: String,
    val language: String,
    val summary: String,
    val image: Image,
    )

data class Image(
    val medium: String,
    val original: String
)