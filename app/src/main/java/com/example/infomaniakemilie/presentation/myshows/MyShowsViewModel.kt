package com.example.infomaniakemilie.presentation.myshows

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infomaniakemilie.common.listOfMyShowsId
import com.example.infomaniakemilie.data.mappers.toShow
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.data.remote.ShowDto
import com.example.infomaniakemilie.domain.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyShowsViewModel @Inject constructor(
    private val apiService : MazeApi,
): ViewModel() {

    /*
     * Random selection of some shows (by ids) I like
     * See Common.kt for the list
     */
    private var pickListShows : List<Int> = emptyList()
    private val _showsList = MutableLiveData<List<Show>>(emptyList())
    val myShowList : LiveData<List<Show>> get() = _showsList
    var errorMessage: String by mutableStateOf("")

    fun getMyShowsById(){
        var listShow: MutableList<Show> = mutableListOf()
        viewModelScope.launch {

            if (pickListShows.isNotEmpty()) pickListShows = emptyList()
            pickListShows = listOfMyShowsId.shuffled().distinct().take(5)

            for (id in pickListShows){
                apiService.getShowById(id).enqueue(object : Callback<ShowDto>{
                    override fun onResponse(call: Call<ShowDto>, response: Response<ShowDto>) {
                        response.body()?.let { show ->
                            //Log.i("VMMYSHOW", "onResponse: ${show.name}")
                            listShow.add(show.toShow())
                            _showsList.value = listShow
                        }
                    }

                    override fun onFailure(call: Call<ShowDto>, t: Throwable) {
                        Log.i("VMMYSHOW", "onFailure: ${t.message}")
                    }
                })
            }
        }

    }
}