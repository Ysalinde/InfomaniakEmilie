package com.example.infomaniakemilie.presentation.myshows


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.infomaniakemilie.presentation.Common.ShowCard

@Composable
fun MyShowsScreen(){
    val viewModel = hiltViewModel<MyShowsViewModel>()
    val myShowsState by viewModel.myShowList.collectAsState()

    LaunchedEffect(true) {
        viewModel.getMyShowsById()
    }

    myShowsState?.let {value ->
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(value) { show ->
                    ShowCard(
                        show = show
                    )
                }
            }
        }
    }

}
