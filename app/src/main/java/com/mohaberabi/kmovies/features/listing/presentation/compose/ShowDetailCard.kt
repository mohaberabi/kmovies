package com.mohaberabi.kmovies.features.listing.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.mohaberabi.kmovies.core.domain.model.Genre
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.presentation.compose.CachedImage
import com.mohaberabi.kmovies.core.presentation.compose.RatingBar
import com.mohaberabi.kmovies.core.presentation.compose.averageColor
import com.mohaberabi.kmovies.core.presentation.design_system.KmoviesTheme
import com.mohaberabi.kmovies.core.presentation.design_system.Radius
import com.mohaberabi.kmovies.core.presentation.design_system.Spacing
import com.mohaberabi.kmovies.features.home.presentation.compose.ShowCard


@Composable
fun ShowDetailCard(
    modifier: Modifier = Modifier,
    show: MediaModel,
    onClick: (MediaModel) -> Unit,
) {


    val defaultColor = MaterialTheme.colorScheme.primaryContainer
    var averageColor by remember {
        mutableStateOf(defaultColor)
    }



    Column(
        modifier = modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(Radius.default))
            .clickable { onClick(show) }
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        averageColor,
                    )
                )
            ),
    ) {


        CachedImage(
            onDone = {
                averageColor = it.toBitmap().averageColor()
            },
            imageUrl = show.posterFullPath,
            modifier = Modifier
                .height(225.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(Radius.default))
        )



        Text(
            text = show.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            modifier = Modifier.padding(horizontal = Spacing.md, vertical = Spacing.xs),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = show.category,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = Spacing.md),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {

            RatingBar(
                starsModifier = Modifier
                    .size(18.dp),
                rating = show.voteAverage / 2
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                text = (show.voteAverage / 2).toString().take(3),
                maxLines = 1,
                color = Color.White.copy(0.7f),
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}

