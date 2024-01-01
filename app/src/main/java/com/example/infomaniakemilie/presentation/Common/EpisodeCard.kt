package com.example.infomaniakemilie.presentation.Common

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.infomaniakemilie.common.spannableStringToAnnotatedString
import com.example.infomaniakemilie.domain.Episode
import com.example.infomaniakemilie.ui.theme.PurpleGrey80
import java.util.Objects

@Composable
fun EpisodeCard(episode: Episode){

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
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ){

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
               val title = if (episode.name.isNullOrBlank()){
                        "${episode.number}. No Title"
                    } else {
                        "${episode.number}. ${episode.name}"
                    }

                Text(
                    text = title,
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

            if(expanded){
                val annotatedSummary = Html.fromHtml(episode.summary, Html.FROM_HTML_MODE_COMPACT)
                val summary = spannableStringToAnnotatedString(annotatedSummary)

                Column {
                    val img = episode.largeImg?.let { it } ?: run { "" }
                    AsyncImage(
                        model = img,
                        contentDescription = episode.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp).align(Alignment.CenterHorizontally),
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = Objects.toString(summary),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}