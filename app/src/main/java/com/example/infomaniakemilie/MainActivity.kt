package com.example.infomaniakemilie

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infomaniakemilie.common.isConnected
import com.example.infomaniakemilie.presentation.allshows.AllShowViewModel
import com.example.infomaniakemilie.presentation.allshows.ShowScreen
import com.example.infomaniakemilie.presentation.dialog.CustomDialog
import com.example.infomaniakemilie.presentation.myshows.MyShowsScreen
import com.example.infomaniakemilie.presentation.myshows.MyShowsViewModel
import com.example.infomaniakemilie.ui.theme.InfomaniakEmilieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfomaniakEmilieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(this)
                }
            }
        }
    }
}

@Composable
private fun MainScreen(context: Context){
    val navController = rememberNavController()
    NavGraph(navController, context)
    navController.navigate("homepage")
}

@Composable
fun NavGraph(navController: NavHostController, context: Context){

    NavHost(
        navController = navController,
        startDestination = "homepage"
    ) {
        composable(route = "homepage"){
            MainScreenLayout(navController, context)
        }

        composable(route = "allshows"){
            ShowAllTheShowsScreen(navController)
        }
        composable(route = "myshows"){
            ShowMyShowsScreen(navController)
        }
    }
}

@Composable
fun DevCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ){
        Row(
            modifier = Modifier.padding(all = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.emilie),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 100 dp
                    .size(100.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Text(stringResource(
                    id = R.string.dev_name),
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    stringResource(id = R.string.welcome),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenLayout(navController: NavHostController, context: Context){
    val openDialog = remember { mutableStateOf(false) }

    Scaffold { contentPadding ->
            Column(
                modifier = Modifier.padding(contentPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally,
            ) {

                DevCard()

                Spacer(modifier = Modifier.width(25.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                ) {
                    Button (
                        onClick = {
                            if(isConnected(context)){
                                navController.navigate("allshows")
                            }else {
                                openDialog.value = true
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.get_all_shows))
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Button (
                        onClick = {
                            if (isConnected(context)) {
                                navController.navigate("myshows")
                            } else {
                                openDialog.value = true
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.myshows))
                    }

                    if(openDialog.value){
                        CustomDialog(openDialogCustom = openDialog, context)
                    }
                }
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowAllTheShowsScreen(navController: NavHostController){
    val viewModel = hiltViewModel<AllShowViewModel>()
    val shows = viewModel.showPagingFlow.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.back))
                },
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
            ShowScreen(shows = shows)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowMyShowsScreen(navController: NavHostController){
    val viewModel = hiltViewModel<MyShowsViewModel>()
    viewModel.getMyShowsById()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.back))
                },
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
            viewModel.myShowList.value?.let {
                MyShowsScreen(it, viewModel.errorMessage)
            }
        }

    }
}


// PREVIEW PART
@Preview
@Composable
fun DevCardPreview(){
    DevCard()
}

@Preview(showBackground = true)
@Composable
private fun MainAppLayoutPreview(){
    val context = ComponentActivity()
    InfomaniakEmilieTheme{
        MainScreenLayout(rememberNavController(), context)
    }
}
