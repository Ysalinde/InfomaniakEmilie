package com.example.infomaniakemilie.presentation.allshows

import android.text.Html
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Show
import com.example.infomaniakemilie.ui.theme.InfomaniakEmilieTheme
import java.util.Objects.toString

@Composable
fun ShowItem(
    show: Show,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(2.dp)
    ) {
        val annotatedSummary = Html.fromHtml(show.summary, Html.FROM_HTML_MODE_COMPACT)
        val summary = spannableStringToAnnotatedString(annotatedSummary)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = show.image,
                contentDescription = show.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = summary,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {

    val text ="<p>The single-camera series that mixes live-action and animation stars Jacob Bertrand as the title character. <b>Kirby Buckets</b> introduces viewers to the vivid imagination of charismatic 13-year-old Kirby Buckets, who dreams of becoming a famous animator like his idol, Mac MacCallister. With his two best friends, Fish and Eli, by his side, Kirby navigates his eccentric town of Forest Hills where the trio usually find themselves trying to get out of a predicament before Kirby's sister, Dawn, and her best friend, Belinda, catch them. Along the way, Kirby is joined by his animated characters, each with their own vibrant personality that only he and viewers can see.</p>\""

    val annotatedSummary = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    val summary = spannableStringToAnnotatedString(annotatedSummary)

    InfomaniakEmilieTheme {
        ShowItem(
            show = Show(
                id = 1,
                name = "Kirby Buckets",
                language = "English",
                summary = toString(summary),
                image = "https://static.tvmaze.com/uploads/images/medium_portrait/1/4600.jpg",
                rating = null
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}