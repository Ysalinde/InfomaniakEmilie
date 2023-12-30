package com.example.infomaniakemilie.presentation.myshows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infomaniakemilie.common.listOfMyShowsId
import com.example.infomaniakemilie.data.local.ShowDatabase
import com.example.infomaniakemilie.data.mappers.toShow
import com.example.infomaniakemilie.data.mappers.toShowEntity
import com.example.infomaniakemilie.data.remote.MazeApi
import com.example.infomaniakemilie.domain.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyShowsViewModel @Inject constructor(
    private val apiService : MazeApi,
    private val showDb: ShowDatabase,
): ViewModel() {

    /*
     * Random selection of some shows (by ids) I like
     * See Common.kt for the list
     */
    private var pickListShows : List<Int> = emptyList()
    private val _showsList = MutableStateFlow<List<Show>>(emptyList())
    val myShowList : StateFlow<List<Show>> get() = _showsList
    var errorMessage: String by mutableStateOf("")

    fun getMyShowsById(){
        var listShow: MutableList<Show> = mutableListOf()
        viewModelScope.launch {

            if (pickListShows.isNotEmpty()) pickListShows = emptyList()
            pickListShows = listOfMyShowsId.shuffled().distinct().take(5)

            for (id in pickListShows){
               val response =  apiService.getShowById(id)
                if(response.isSuccessful) {
                    val showDto = response.body()
                    showDto?.let {
                        showDb.dao.insert(it.toShowEntity())
                        listShow.add(it.toShow())
                    }
                }
            }
            _showsList.value = listShow
        }
    }
}

/*
 * Initially I did it in another way to do it is with a callback and override the functions
 * onResponse and onFailure using apiService.getShowById(id) and changing in Maze API from:
 * suspend fun getShowById(@Path("id") showId: Int): Response<ShowDto>
 * to
 * fun getShowById(@Path("id") showId: Int): Callback<ShowDto>
 */

/*
apiService.getShowById(id) .enqueue(object : Callback<ShowDto>{
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
*/