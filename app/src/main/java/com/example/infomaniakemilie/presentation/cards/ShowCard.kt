package com.example.infomaniakemilie.presentation.cards

import android.text.Html
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Show
import com.example.infomaniakemilie.ui.theme.PurpleGrey80
import java.util.Objects.toString

@Composable
fun ShowCard(show: Show){
    // Variable to remember the actual state of the card
    var expanded by remember { mutableStateOf (false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clickable(
                onClick = { expanded = !expanded }
            ),
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = PurpleGrey80,
        ),
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(!expanded){
                    val img = show.mediumImage?.let { it }?: run { "" }
                    AsyncImage(
                        model = img,
                        contentDescription = show.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    )
                }

                Text(
                    text = show.name,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(6f),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.2f)
                        .rotate(rotationState),
                    onClick = {
                        expanded = !expanded
                    }){
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down Arrow"
                        )
                }
            }

            // Expanded part of the card
            if(expanded){
                val annotatedSummary = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
                val summary = spannableStringToAnnotatedString(annotatedSummary)

                Column {
                    val img = show.largeImage?.let { it }?: run { "" }
                    AsyncImage(
                        model = img,
                        contentDescription = show.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp).align(CenterHorizontally),
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        show.rating?.let {
                            Text(
                                text = "Rating: ${(it*10).toInt()} %",
                                style = MaterialTheme.typography.bodyMedium)
                        } ?: run {
                            Text(text = "No Rating found.")
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = toString(summary),
                            style = MaterialTheme.typography.titleSmall,
                            )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowCard() {
    val text ="<p>The single-camera series that mixes live-action and animation stars Jacob Bertrand as the title character. <b>Kirby Buckets</b> introduces viewers to the vivid imagination of charismatic 13-year-old Kirby Buckets, who dreams of becoming a famous animator like his idol, Mac MacCallister. With his two best friends, Fish and Eli, by his side, Kirby navigates his eccentric town of Forest Hills where the trio usually find themselves trying to get out of a predicament before Kirby's sister, Dawn, and her best friend, Belinda, catch them. Along the way, Kirby is joined by his animated characters, each with their own vibrant personality that only he and viewers can see.</p>\""
    val annotatedSummary = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    val summary = spannableStringToAnnotatedString(annotatedSummary)
    val show = Show(
        id = 1,
        name = "Kirby Buckets",
        language = "English",
        summary = toString(summary),
        mediumImage = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
        largeImage = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
        rating = null,
        averageRuntime = 25,
        yearPremiered = "2023",
    )

    ShowCard(show)
}
