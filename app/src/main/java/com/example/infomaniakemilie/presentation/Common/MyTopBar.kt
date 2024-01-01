package com.example.infomaniakemilie.presentation.Common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.infomaniakemilie.ui.theme.BlueMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    topBarTitle: String,
    navController: NavHostController
) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = topBarTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                ) },
        actions = {
            IconButton(
                onClick = {navController.navigate("homepage")},
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Homepage"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlueMedium)
    )
}