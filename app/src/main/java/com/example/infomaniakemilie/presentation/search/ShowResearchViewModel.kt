package com.example.infomaniakemilie.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infomaniakemilie.data.local.ShowDatabase
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.domain.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowResearchViewModel @Inject constructor(
    private val apiService : MazeApi,
    private val showDb: ShowDatabase,
): ViewModel() {

    private val _searchResults = MutableLiveData<List<Show>>()
    val searchResults: LiveData<List<Show>> get() = _searchResults
    var errorMessage: String by mutableStateOf("")

    fun searchShows(query: String){
        viewModelScope.launch {
            try {
                val response = apiService.getSearchByValue(query)

                if (response.isSuccessful) {
                    val searchResponseList = response.body()
                    searchResponseList?.let {
                        _searchResults.value = it.map { searchResultDto ->
                            Show(
                                id = searchResultDto.show.id,
                                name = searchResultDto.show.name,
                                language = searchResultDto.show.language,
                                summary = searchResultDto.show.summary,
                                mediumImage = searchResultDto.show.image?.medium,
                                largeImage = searchResultDto.show.image?.original,
                                rating = searchResultDto.show.rating?.average,
                                averageRuntime = searchResultDto.show.averageRuntime,
                                yearPremiered = searchResultDto.show.premiered?.take(4),
                            )
                        }
                    }
                    Log.i("SearchList", "My Shows: ${_searchResults.value}")
                }

            } catch (e: Exception){
                errorMessage = "Error Message: ${e.message}"
            }
        }
    }

}