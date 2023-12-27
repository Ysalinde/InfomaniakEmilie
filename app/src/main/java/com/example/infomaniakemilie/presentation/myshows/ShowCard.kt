package com.example.infomaniakemilie.presentation.myshows

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Show
import java.util.Objects

@Composable
fun ShowCard(show: Show){
    // Variable to remember the actual state of the card
    var expanded by remember { mutableStateOf (false) }

    val annotatedSummary = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
    val summary = spannableStringToAnnotatedString(annotatedSummary)

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                onClick = { expanded = !expanded }
            ),
        shape = RoundedCornerShape(7.dp)
    ) {
        Column {
            Text(
                text = show.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.labelLarge,
            )

            if(expanded){
                Column {
                    AsyncImage(
                        model = show.image,
                        contentDescription = show.name,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = show.name,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = summary,
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
        summary = Objects.toString(summary),
        image = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
        rating = null,
    )

    ShowCard(show)
}
