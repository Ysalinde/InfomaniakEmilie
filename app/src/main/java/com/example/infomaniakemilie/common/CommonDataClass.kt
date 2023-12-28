package com.example.infomaniakemilie.common

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