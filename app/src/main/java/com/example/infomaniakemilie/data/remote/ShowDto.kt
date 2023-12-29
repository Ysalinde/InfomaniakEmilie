package com.example.infomaniakemilie.data.remote

import com.example.infomaniakemilie.common.Average
import com.example.infomaniakemilie.common.Image

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
    val averageRuntime: Int?,
    val premiered: String?,
)