package com.example.infomaniakemilie.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This file should interface all the Api Maze TV requests
 *
 */
interface MazeApi {

    @GET("shows")
    suspend fun getShows(
        @Query("pages") page: Int,
        @Query("pageSize") pageSize: Int,
    ): List<ShowDto>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}