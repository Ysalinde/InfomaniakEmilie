package com.example.infomaniakemilie.presentation.cards

import android.content.Context
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.common.isConnected
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Season
import com.example.infomaniakemilie.presentation.dialog.CustomDialog
import java.util.Objects

@Composable
fun SeasonCard(navController: NavHostController, context: Context, season: Season) {

    val openDialog = remember { mutableStateOf(false) }
    val seasonId = season.id

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.clickable(
            onClick = {
                if (isConnected(context)) {
                    navController.navigate("season/${seasonId}")
                } else {
                    openDialog.value = true
                }
        }),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(
                    horizontal = 16.dp,
                    vertical = 3.dp
                ),
        ) {

            AsyncImage(
                model = season.largeImg,
                contentDescription = "Episode ${season.episodeOrder}, Season ${season.number}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(13.dp))
                    .border(
                        BorderStroke(1.dp, Color.LightGray)
                    )
                    .height(150.dp)
                    .width(125.dp)
                    .align(Top),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = "Season ${season.number}: ",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                )

                if (season.summary.isNullOrBlank()) {
                    Text(
                        text = stringResource(id = R.string.no_summary),
                        style = MaterialTheme.typography.titleSmall,
                    )
                } else {
                    val annotatedSummary =
                        Html.fromHtml(season.summary, Html.FROM_HTML_MODE_COMPACT)
                    val summary = spannableStringToAnnotatedString(annotatedSummary)

                    Text(
                        text = Objects.toString(summary),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }


            }
        }

        if(openDialog.value){
            CustomDialog(openDialogCustom = openDialog, context)
        }
    }
}

@Preview
@Composable
fun PreviewSeasonCard(){
    //Data values for Broke Girls Season 1
    val season = Season(
        id = 614,
        number = 1,
        episodeOrder = 24,
        premiereDate = "2011",
        mediumImg = "https://static.tvmaze.com/uploads/images/medium_portrait/42/105209.jpg",
        largeImg = "https://static.tvmaze.com/uploads/images/original_untouched/42/105209.jpg",
        summary = "<p>The season introduces Max Black, a sarcastic below-the-poverty-line waitress, and Caroline Channing, a disgraced New York socialite turned waitress, who both pool their money together to pay for their future cupcake business.</p>",
    )

    val context = ComponentActivity()
    SeasonCard(rememberNavController(), context ,season = season)

}