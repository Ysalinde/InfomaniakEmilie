package com.example.infomaniakemilie.data.remote.dto

import com.example.infomaniakemilie.common.Average
import com.example.infomaniakemilie.common.Image

data class EpisodeDto (
    val id: Int,
    val name: String?,
    val season: Int,
    val number: Int,
    val runtime: Int?,
    val rating: Average?,
    val image: Image?,
    val summary: String?
)
