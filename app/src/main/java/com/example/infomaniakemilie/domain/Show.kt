package com.example.infomaniakemilie.domain

data class Show(
    val id: Int,
    val name: String,
    val language: String,
    val summary: String?,
    val image: String?,
    val rating: Double?,
)
