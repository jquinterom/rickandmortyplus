package com.example.rickandmortyplus.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.rickandmortyplus.EMPTY_STR
import com.example.rickandmortyplus.R
import com.example.rickandmortyplus.STATUS_ALIVE
import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.model.Episode
import com.example.rickandmortyplus.model.Location
import com.example.rickandmortyplus.ui.theme.RickAndMortyPlusTheme

@Composable
fun CharacterCard(
    character: Character
) {
    val colorStatus =
        if (character.status.toString() == STATUS_ALIVE) {
            ColorFilter.tint(color = Color.Green)
        } else {
            ColorFilter.tint(color = Color.Red)
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dimensionResource(id = R.dimen.padding_all_card)
            )
            .shadow(
                elevation = dimensionResource(id = R.dimen.shadow_card),
                spotColor = MaterialTheme.colors.onPrimary
            )
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (image, textBody) = createRefs()

            SubcomposeAsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                alignment = Alignment.Center,
                model = character.image,
                contentDescription = null,
            ) {

                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier.clickable { },
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    SubcomposeAsyncImageContent()
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_start_card))
                    .fillMaxWidth()
                    .constrainAs(textBody) {
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Column() {
                    Text(
                        text = character.name ?: EMPTY_STR,
                        style = MaterialTheme.typography.h6,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .height(10.dp)
                                .padding(end = 8.dp),
                            painter = painterResource(id = R.drawable.ic_circle),
                            contentDescription = "status",
                            colorFilter = colorStatus
                        )
                        Text(
                            style = MaterialTheme.typography.body2,
                            text = character.status + " - " + character.species
                        )
                    }
                }

                Column(

                ) {
                    Text(
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        text = "Last known location:",
                    )
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = character.location?.name ?: ""
                    )
                }

                Column() {
                    Text(
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        text = "First seen in:"
                    )
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = character.episode?.get(0)?.name ?: ""
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCharacterCard() {
    RickAndMortyPlusTheme() {
        Surface() {
            CharacterCard(Character("1", "", "", "", "", Location(""), listOf<Episode>()))
        }
    }
}