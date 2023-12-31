package com.example.infomaniakemilie.presentation.season

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infomaniakemilie.data.local.EpisodeDatabase
import com.example.infomaniakemilie.data.local.SeasonDatabase
import com.example.infomaniakemilie.data.mappers.toSeason
import com.example.infomaniakemilie.data.mappers.toSeasonEntity
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.domain.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowSeasonViewModel @Inject constructor(
    private val apiService : MazeApi,
    private val seasonDb: SeasonDatabase,
    private val episodeDb: EpisodeDatabase,
): ViewModel(){

    private val _seasonsList = MutableLiveData<List<Season>>()
    val seasonsList: LiveData<List<Season>> get() = _seasonsList
    var errorMessage: String by mutableStateOf("")

    fun getSeasonByShowId(showId: Int){
        viewModelScope.launch {
            try {
                val response = apiService.getSeasonsOfShow(showId)
                if(response.isSuccessful){
                    val seasonsResponseList = response.body()
                    seasonsResponseList?.let { listSeason ->
                        val entityList = listSeason.map { it.toSeasonEntity() }
                        seasonDb.dao.upsertAll(entityList)
                        _seasonsList.value = entityList.map { it.toSeason() }
                    }
                }
            }catch (e: Exception){
                errorMessage = "Error: ${e.message}"
            }
        }
    }


}