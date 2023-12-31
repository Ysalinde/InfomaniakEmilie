package com.example.infomaniakemilie.presentation.episodes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infomaniakemilie.data.local.EpisodeDatabase
import com.example.infomaniakemilie.data.mappers.toEpisode
import com.example.infomaniakemilie.data.mappers.toEpisodeEntity
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.domain.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowEpisodesViewModel @Inject constructor(
    private val apiService : MazeApi,
    private val episodeDb: EpisodeDatabase,
): ViewModel() {

    private val _episodesList = MutableLiveData<List<Episode>>()
    val episodesList: LiveData<List<Episode>> get() = _episodesList
    var errorMessage: String by mutableStateOf("")

    fun getEpisodesBySeasonId(seasonId: Int){
        viewModelScope.launch{
            try {
                val response = apiService.getEpisodesOfSeason(seasonId)

                if (response.isSuccessful) {
                    val episodesResponseList = response.body()
                    episodesResponseList?.let { episodeSeason ->
                        val entityList = episodeSeason.map { it.toEpisodeEntity() }
                        episodeDb.dao.upsertAll(entityList)
                        _episodesList.postValue(entityList.map { it.toEpisode() })
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            }
        }
    }
}
