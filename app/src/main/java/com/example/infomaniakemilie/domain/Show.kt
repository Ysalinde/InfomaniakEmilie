package com.example.infomaniakemilie.domain

data class Show(
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val mediumImage: String?,
    val largeImage: String?,
    val rating: Double?,
)
