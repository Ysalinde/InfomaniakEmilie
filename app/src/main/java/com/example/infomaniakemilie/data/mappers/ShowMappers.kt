package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.entity.ShowEntity
import com.example.infomaniakemilie.data.remote.dto.ShowDto
import com.example.infomaniakemilie.domain.Show


/*
 * Mapper page for Show Datas
 *
 * 1. Dto -> Entity
 * 2. Entity -> Object
 * 3. Dto -> Object
 */
fun ShowDto.toShowEntity(): ShowEntity {
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
    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        mediumImage = mediumImg ,
        largeImage= largeImg ,
        rating = rating,
        averageRuntime = averageRuntime,
        yearPremiered = premiered,
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