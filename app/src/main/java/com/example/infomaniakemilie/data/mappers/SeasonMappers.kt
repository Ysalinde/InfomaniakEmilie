package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.entity.SeasonEntity
import com.example.infomaniakemilie.data.remote.dto.SeasonDto
import com.example.infomaniakemilie.domain.Season

fun SeasonDto.toSeasonEntity(): SeasonEntity {
    val year = premiereDate?.let {
        it.take(4)
    } ?: kotlin.run { null }

    return SeasonEntity(
        id = id,
        number = number,
        episodeOrder = episodeOrder,
        premiereDate = year,
        mediumImg = image?.medium,
        largeImg = image?.original,
        summary = summary,
    )
}

fun SeasonEntity.toSeason(): Season {
    return Season(
        id = id,
        number = number,
        episodeOrder = episodeOrder,
        premiereDate = premiereDate,
        mediumImg = mediumImg,
        largeImg = largeImg,
        summary = summary,
    )
}