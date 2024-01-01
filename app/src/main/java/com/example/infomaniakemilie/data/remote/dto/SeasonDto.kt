package com.example.infomaniakemilie.data.remote.dto

import com.example.infomaniakemilie.common.Image


/*
 * Season - Data to object
 * Coming from JSON request from API
 */
data class SeasonDto(
    val id: Int,
    val number: Int,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val image: Image?,
    val summary: String?,
)
