package com.example.infomaniakemilie.presentation.myshows


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.infomaniakemilie.domain.Show

@Composable
fun MyShowsScreen(myShows: List<Show>, errorMessage: String){

    LaunchedEffect(Unit, block = {
        Log.i("SHOWS", "${myShows}")
    })
    if (errorMessage.isEmpty()) {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(myShows) { show ->
                    ShowCard(
                        show = show
                    )
                }
            }
    }
    } else {
        Text(errorMessage)
    }
}