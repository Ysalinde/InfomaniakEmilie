package com.example.infomaniakemilie.presentation.search

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.common.isConnected
import com.example.infomaniakemilie.presentation.Common.CustomDialog
import com.example.infomaniakemilie.presentation.Common.MyTopBar
import com.example.infomaniakemilie.presentation.Common.ShowItem
import com.example.infomaniakemilie.ui.theme.BlueLight
import com.example.infomaniakemilie.ui.theme.InfomaniakEmilieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowResearch(navController: NavHostController, context: Context){
    val viewModel = hiltViewModel<ShowResearchViewModel>()
    val searchResults by viewModel.searchResults.observeAsState()
    var searchText by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BlueLight,
        topBar = {
            MyTopBar(
                topBarTitle = stringResource(id = R.string.reseach_title),
                navController,
            )
        },
    ){ contentPadding ->

        Column(modifier = Modifier.padding(contentPadding)){

            OutlinedTextField(
                value = searchText,
                label = { Text("Research") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 4.dp),
                onValueChange = {
                    if(isConnected(context)){
                        searchText = it
                        viewModel.searchShows(it)
                    }
                    else{
                        openDialog.value = true
                    }
                },
            )

            searchResults?.let { value ->
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(value) { show ->
                            ShowItem(
                                show = show,
                                navController,
                                context
                            )
                        }
                    }
                }
            }

            if(openDialog.value){
                CustomDialog(openDialogCustom = openDialog, context)
            }
        }

    }
}

@Preview
@Composable
private fun ShowResearchPreview(){
    val context = ComponentActivity()
    InfomaniakEmilieTheme {
        ShowResearch(rememberNavController(), context)
    }
}