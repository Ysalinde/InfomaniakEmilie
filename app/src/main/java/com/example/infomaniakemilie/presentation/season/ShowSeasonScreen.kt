package com.example.infomaniakemilie.presentation.season

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.presentation.cards.SeasonCard
import com.example.infomaniakemilie.ui.theme.BlueLight
import com.example.infomaniakemilie.ui.theme.BlueMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSeasonScreen(
    navController: NavHostController,
    context: Context
){
    val showId = navController.currentBackStackEntry?.arguments?.getInt("showId")
    val viewModel = hiltViewModel<ShowSeasonViewModel>()
    val seasons by viewModel.seasonsList.observeAsState()


    LaunchedEffect(key1 = true){
        showId?.let {
            viewModel.getSeasonByShowId(it)
        }
    }

    Scaffold(
        containerColor = BlueLight,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.back))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlueMedium),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { contentPadding ->

        seasons?.let { value ->
            Column(modifier = Modifier.padding(contentPadding)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent)
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(value) { season ->
                        SeasonCard(
                            navController,
                            context,
                            season
                        )

                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

}