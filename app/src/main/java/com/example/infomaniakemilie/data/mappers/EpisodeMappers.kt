package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.entity.EpisodeEntity
import com.example.infomaniakemilie.data.remote.dto.EpisodeDto
import com.example.infomaniakemilie.domain.Episode

fun EpisodeDto.toEpisodeEntity(): EpisodeEntity{
    return EpisodeEntity(
        id = id,
        name = name,
        season = season,
        number = number,
        runtime = runtime,
        rating = rating?.average,
        mediumImg = image?.medium,
        largeImg = image?.original,
        summary = summary,
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        season = season,
        number = number,
        runtime = runtime,
        rating = rating,
        mediumImg = mediumImg,
        largeImg = largeImg,
        summary = summary,
    )
}

fun EpisodeDto.toEpisode(): Episode{
    return Episode(
        id = id,
        name = name,
        season = season,
        number = number,
        runtime = runtime,
        rating = rating?.average,
        mediumImg = image?.medium,
        largeImg = image?.original,
        summary = summary,
    )
}