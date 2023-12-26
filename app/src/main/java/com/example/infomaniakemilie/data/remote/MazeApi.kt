package com.example.infomaniakemilie.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This file should interface all the requests affiliated to Api Maze TV
 *
 */
interface MazeApi {

    // SHOWS RESQUESTS PART

    /*
     * This request will grab all the shows presented on a page
     * @arg page: actual page of the request
     * @arg pageSize: number of shows present on the page
     * for MazeTv - 250 shows for one page
     *
     * @return List<ShowDto>: list of all the shows datas
     */
    @GET("shows")
    suspend fun getShows(
        @Query("pages") page: Int,
        @Query("pageSize") pageSize: Int,
    ): List<ShowDto>

    /*
     * The request will find a show by the id
     * @arg id: id of the show
     *
     * @return Call<ShowDto>: the response with the JSON to parse
     */
    @GET("/shows/{id}")
    fun getShowById(@Path("id") showId: Int): Call<ShowDto>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}