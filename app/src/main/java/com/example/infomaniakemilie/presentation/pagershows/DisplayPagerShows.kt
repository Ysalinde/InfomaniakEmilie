package com.example.infomaniakemilie.presentation.pagershows

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.ui.theme.BlueLight
import com.example.infomaniakemilie.ui.theme.BlueMedium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayPagerShows(navController: NavHostController, context: Context){
    val viewModel = hiltViewModel<PagerShowViewModel>()
    val shows = viewModel.showPagingFlow.collectAsLazyPagingItems()
    Scaffold(
        containerColor = BlueLight,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.back))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlueMedium),
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("homepage")}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ){ contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            ShowScreen(
                shows = shows,
                navController,
                context
            )
        }

    }
}
