package com.example.infomaniakemilie.presentation.pagershows

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.infomaniakemilie.domain.Show
import com.example.infomaniakemilie.presentation.Common.ShowItem

@Composable
fun ShowScreen(
    shows: LazyPagingItems<Show>,
    navController: NavHostController,
    contextApp: Context
) {
    val contextLocal = LocalContext.current
    LaunchedEffect(key1 = shows.loadState) {
        if(shows.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                contextLocal,
                "Error: " + (shows.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(shows.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(color = Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(shows) { show ->
                    if(show != null) {
                        ShowItem(
                            show = show,
                            navController,
                            contextApp
                        )

                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                item {
                    if(shows.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}