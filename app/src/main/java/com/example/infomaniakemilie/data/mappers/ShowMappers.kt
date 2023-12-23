package com.example.infomaniakemilie.data.mappers

import com.example.infomaniakemilie.data.local.ShowEntity
import com.example.infomaniakemilie.data.remote.ShowDto
import com.example.infomaniakemilie.domain.Show

fun ShowDto.toShowEntity(): ShowEntity{
    return ShowEntity(
        id = id,
        name = name,
        language = language,
        summary = summary,
        image = image.medium
    )
}

fun ShowEntity.toShow(): Show {
    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        img = image
    )
}

fun ShowDto.toShow(): Show{
    return Show(
        id = id,
        name = name,
        language = language,
        summary = summary,
        img = image.medium
    )
}