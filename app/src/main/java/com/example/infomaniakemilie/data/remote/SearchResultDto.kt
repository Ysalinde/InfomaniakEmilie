package com.example.infomaniakemilie.data.remote

import com.example.infomaniakemilie.data.remote.dto.ShowDto

data class SearchResultDto(
    val score: Double,
    val show: ShowDto,
)
