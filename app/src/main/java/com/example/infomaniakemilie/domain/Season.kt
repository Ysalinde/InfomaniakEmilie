package com.example.infomaniakemilie.domain

data class Season(
    val id: Int,
    val number: Int,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val mediumImg: String?,
    val largeImg: String?,
    val summary: String?,
)
