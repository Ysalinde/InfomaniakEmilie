package com.example.infomaniakemilie

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.infomaniakemilie.common.isConnected
import com.example.infomaniakemilie.presentation.Common.DevCard
import com.example.infomaniakemilie.presentation.Common.CustomDialog
import com.example.infomaniakemilie.presentation.episodes.ShowEpisodesScreen
import com.example.infomaniakemilie.presentation.myshows.DisplayMyShows
import com.example.infomaniakemilie.presentation.pagershows.DisplayPagerShows
import com.example.infomaniakemilie.presentation.search.ShowResearch
import com.example.infomaniakemilie.presentation.season.ShowSeasonScreen
import com.example.infomaniakemilie.ui.theme.BlueDarker
import com.example.infomaniakemilie.ui.theme.BlueLight
import com.example.infomaniakemilie.ui.theme.BlueMedium
import com.example.infomaniakemilie.ui.theme.InfomaniakEmilieTheme
import com.example.infomaniakemilie.ui.theme.Pink80
import com.example.infomaniakemilie.ui.theme.Purple80
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
                    color = BlueLight
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
        composable(route = "pagershows"){
            DisplayPagerShows(navController, context)
        }
        composable(route = "myshows"){
            DisplayMyShows(navController)
        }
        composable(route = "research"){
            ShowResearch(navController, context)
        }
        composable(
            route = "show/{showId}",
            arguments = listOf(navArgument("showId") { type = NavType.IntType })
        ){
            ShowSeasonScreen(navController, context)
        }
        composable(
            route = "season/{seasonId}",
            arguments = listOf(navArgument("seasonId") { type = NavType.IntType })
        ){
            ShowEpisodesScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenLayout(navController: NavHostController, context: Context){
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        contentColor = Color.Black,
        containerColor = BlueLight,
        ){ contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(16.dp),
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

                    Row(modifier = Modifier.align(End)){

                        Text(
                            text = stringResource(id = R.string.search),
                            textAlign = TextAlign.End,
                            color = BlueMedium,
                            modifier = Modifier.align(
                                Alignment.CenterVertically
                            ),
                        )

                        IconButton(
                            onClick = {
                                if (isConnected(context)) {
                                    navController.navigate("research")
                                } else {
                                    openDialog.value = true
                                }},
                            ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Magnifier",
                                tint = BlueDarker,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }


                    Spacer(modifier = Modifier.width(6.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp,
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Purple80,
                        ),
                    ) {
                        Column(modifier = Modifier.clickable(onClick = {
                            if (isConnected(context)) {
                                navController.navigate("pagershows")
                            } else {
                                openDialog.value = true
                            }
                        })) {

                            Image(
                                painter = painterResource(R.drawable.mandalorian),
                                contentDescription = null, // decorative
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                            )

                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = stringResource(id = R.string.get_all_shows),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = stringResource(id = R.string.get_shows_content),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp,
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Pink80,
                        ),
                    ) {
                        Column(modifier = Modifier.clickable(onClick = {
                            if (isConnected(context)) {
                                navController.navigate("myshows")
                            } else {
                                openDialog.value = true
                            }
                        })) {

                            Image(
                                painter = painterResource(R.drawable.maomao),
                                contentDescription = null, // decorative
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth()
                            )

                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = stringResource(id = R.string.my_shows),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(
                                    text = stringResource(id = R.string.my_shows_content),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                    }
                }
            }

        if(openDialog.value){
            CustomDialog(openDialogCustom = openDialog, context)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainAppLayoutPreview(){
    val context = ComponentActivity()
    InfomaniakEmilieTheme{
        MainScreenLayout(rememberNavController(), context)
    }
}
