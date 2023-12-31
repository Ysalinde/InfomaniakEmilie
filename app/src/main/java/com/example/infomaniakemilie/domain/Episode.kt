package com.example.infomaniakemilie.domain

data class Episode(
    val id: Int,
    val name: String?,
    val season: Int,
    val number: Int,
    val runtime: Int?,
    val rating: Double?,
    val mediumImg: String?,
    val largeImg: String?,
    val summary: String?
)
