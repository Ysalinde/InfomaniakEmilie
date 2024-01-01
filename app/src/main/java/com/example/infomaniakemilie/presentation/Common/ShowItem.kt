package com.example.infomaniakemilie.presentation.Common

import android.content.Context
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.infomaniakemilie.common.isConnected
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Show
import com.example.infomaniakemilie.ui.theme.InfomaniakEmilieTheme
import java.util.Objects.toString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowItem(
    show: Show,
    navController: NavHostController,
    context: Context,
) {

    val openDialog = remember { mutableStateOf(false) }
    val showId = show.id

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        ),
        onClick = {
            if (isConnected(context)) {
                navController.navigate("show/${showId}")
            } else {
                openDialog.value = true
            }
        },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(
                    horizontal = 16.dp,
                    vertical = 3.dp
                )
        ) {

            AsyncImage(
                model = show.largeImage,
                contentDescription = show.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(13.dp))
                    .border(
                        BorderStroke(1.dp, Color.LightGray)
                    )
                    .height(150.dp)
                    .width(125.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = show.name,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.width(13.dp))

                show.rating?.let {
                    Text(
                        text = "Rating: ${(it*10).toInt()} %",
                        style = MaterialTheme.typography.bodyMedium)
                } ?: run {
                    Text(text = "No Rating")
                }

                Spacer(modifier = Modifier.width(13.dp))

                show.yearPremiered?.let {
                    Text(
                        text = show.yearPremiered,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            show.averageRuntime?.let {
                Text(
                    text = "${show.averageRuntime} min",
                    modifier = Modifier.align(CenterVertically)
                )
            }
        }
    }

    if(openDialog.value){
        CustomDialog(openDialogCustom = openDialog, context)
    }

}

@Preview
@Composable
fun ShowItemPreview() {

    val text ="<p>The single-camera series that mixes live-action and animation stars Jacob Bertrand as the title character. <b>Kirby Buckets</b> introduces viewers to the vivid imagination of charismatic 13-year-old Kirby Buckets, who dreams of becoming a famous animator like his idol, Mac MacCallister. With his two best friends, Fish and Eli, by his side, Kirby navigates his eccentric town of Forest Hills where the trio usually find themselves trying to get out of a predicament before Kirby's sister, Dawn, and her best friend, Belinda, catch them. Along the way, Kirby is joined by his animated characters, each with their own vibrant personality that only he and viewers can see.</p>\""

    val annotatedSummary = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    val summary = spannableStringToAnnotatedString(annotatedSummary)
    val context = ComponentActivity()

    InfomaniakEmilieTheme {
        ShowItem(
            show = Show(
                id = 1,
                name = "Kirby Buckets",
                language = "English",
                summary = toString(summary),
                mediumImage = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
                largeImage = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
                rating = null,
                averageRuntime = 25,
                yearPremiered = "2023"
            ),
            rememberNavController(),
            context
        )
    }
}