package com.example.infomaniakemilie.presentation.Common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.infomaniakemilie.R
import com.example.infomaniakemilie.ui.theme.PurpleGrey80


@Composable
fun DevCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80)
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
                Text(
                    stringResource(
                    id = R.string.dev_name),
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    stringResource(id = R.string.my_card_content),
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun DevCardPreview(){
    DevCard()
}
