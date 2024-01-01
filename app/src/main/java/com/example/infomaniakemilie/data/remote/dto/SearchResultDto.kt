package com.example.infomaniakemilie.data.remote.dto

/*
 * SearchResult - Data to object
 * Coming from JSON request from API
 */
data class SearchResultDto(
    val score: Double,
    val show: ShowDto,
)
