package com.example.infomaniakemilie.data.remote

import com.example.infomaniakemilie.data.remote.dto.EpisodeDto
import com.example.infomaniakemilie.data.remote.dto.SearchResultDto
import com.example.infomaniakemilie.data.remote.dto.SeasonDto
import com.example.infomaniakemilie.data.remote.dto.ShowDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This file should interface all the requests affiliated to Api Maze TV
 *
 */
interface MazeApi {

    // SHOWS REQUESTS PART

    /*
     * This request will grab all the shows presented on a page
     * @arg page: actual page of the request
     * @arg pageSize: number of shows present on the page
     * for MazeTv - 250 shows per page
     *
     * @return List<ShowDto>: list of the shows datas per page
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
     * @return Response<ShowDto>: the response with the JSON body
     */
    @GET("/shows/{id}")
    suspend fun getShowById(@Path("id") showId: Int): Response<ShowDto>


    /*
     * The request will find shows by a query
     * @arg q: query entered by a user
     *
     * @return Response<List<SearchResultDto>>: the response with the List of the results
     */
    @GET("/search/shows")
    suspend fun getSearchByValue(@Query("q") query: String): Response<List<SearchResultDto>>

    // END SHOWS REQUESTS PART

    // SEASON REQUESTS PART

    /*
     * The request will find the season(s) of a show by its ID
     * @arg id: id of the show
     *
     * @return Response<List<SeasonDto>>: the response with the list of the season(s)
     */
    @GET("/shows/{id}/seasons")
    suspend fun getSeasonsOfShow(@Path("id") showId: Int): Response<List<SeasonDto>>


    // END SEASON REQUEST PART

    // EPISODE REQUEST PART
    /*
         * The request will find the episode(s) of a season by its ID
         * @arg id: id of the season
         *
         * @return Response<List<EpisodeDto>>: the response with the list of the episode(s)
         */
    @GET("/seasons/{id}/episodes")
    suspend fun getEpisodesOfSeason(@Path("id") seasonId: Int): Response<List<EpisodeDto>>

    companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
    }
}