package com.example.infomaniakemilie.data.remote

/*
 * Show - Data to object
 * Coming from JSON request from API
 */
data class ShowDto(
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val image: Image?,
    val rating: Average?,
)

/*
 * Data class to access to img address from API
 */
data class Image(
    val medium: String,
    val original: String
)

/*
 * Data class to access to the rating
 */
data class Average(
    val note: Double?,
)