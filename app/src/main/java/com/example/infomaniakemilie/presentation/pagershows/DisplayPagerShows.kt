package com.example.infomaniakemilie.presentation.pagershows

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.presentation.Common.MyTopBar
import com.example.infomaniakemilie.ui.theme.BlueLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayPagerShows(navController: NavHostController, context: Context){
    val viewModel = hiltViewModel<PagerShowViewModel>()
    val shows = viewModel.showPagingFlow.collectAsLazyPagingItems()

    Scaffold(
        containerColor = BlueLight,
        topBar = {
            MyTopBar(
                topBarTitle = stringResource(id = R.string.pager_title),
                navController,
            )
        }
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
