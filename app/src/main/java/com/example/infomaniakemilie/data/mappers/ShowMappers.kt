package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.ShowEntity
import com.example.infomaniakemilie.data.remote.ShowDto
import com.example.infomaniakemilie.domain.Show

// For safeLet function, refer to Common file
fun ShowDto.toShowEntity(): ShowEntity{
    return ShowEntity(
        id = id,
        name = name,
        language = language,
        summary = summary,
        image = image?.medium,
        rating = rating?.note,
    )
}

fun ShowEntity.toShow(): Show {
    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        image = image,
        rating = rating,
    )
}

fun ShowDto.toShow(): Show {
    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        image = image?.medium,
        rating = rating?.note,
        )
}