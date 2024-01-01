package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.entity.SeasonEntity
import com.example.infomaniakemilie.data.remote.dto.SeasonDto
import com.example.infomaniakemilie.domain.Season

/*
 * Mapper page for Seasons Datas
 *
 * 1. Dto -> Entity
 * 2. Entity -> Object
 */

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