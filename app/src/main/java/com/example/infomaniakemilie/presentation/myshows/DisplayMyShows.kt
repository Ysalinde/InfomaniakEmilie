package com.example.infomaniakemilie.presentation.myshows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.presentation.Common.MyTopBar
import com.example.infomaniakemilie.ui.theme.BlueLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayMyShows(navController: NavHostController){
    Scaffold(
        containerColor = BlueLight,
        topBar = {
            MyTopBar(
                topBarTitle = stringResource(id = R.string.my_show_title),
                navController,
            )
        },
    ){ contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            MyShowsScreen()
        }
    }
}