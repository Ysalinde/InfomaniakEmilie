package com.example.infomaniakemilie.domain

/*
 * Data Class of Show used to display UI information
 */
data class Show(
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val mediumImage: String?,
    val largeImage: String?,
    val rating: Double?,
    val averageRuntime: Int?,
    val yearPremiered: String?
)
