package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.ShowEntity
import com.example.infomaniakemilie.data.remote.ShowDto
import com.example.infomaniakemilie.domain.Show

// For safeLet function, refer to Common file
fun ShowDto.toShowEntity(): ShowEntity{
    val year = premiered?.let {
        it.take(4)
    } ?: kotlin.run { null }

    return ShowEntity(
        id = id,
        name = name,
        language = language,
        summary = summary,
        mediumImg = image?.medium,
        largeImg = image?.original,
        rating = rating?.average,
        averageRuntime = averageRuntime,
        premiered = year,
    )
}

fun ShowEntity.toShow(): Show {
    val year = premiered?.let {
        it.take(4)
    } ?: kotlin.run { null }

    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        mediumImage = mediumImg ,
        largeImage= largeImg ,
        rating = rating,
        averageRuntime = averageRuntime,
        yearPremiered = year,
    )
}

fun ShowDto.toShow(): Show {

    val year = premiered?.let {
        it.take(4)
    } ?: kotlin.run { null }

    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        mediumImage = image?.medium,
        largeImage = image?.original,
        rating = rating?.average,
        averageRuntime = averageRuntime,
        yearPremiered = year,
        )
}