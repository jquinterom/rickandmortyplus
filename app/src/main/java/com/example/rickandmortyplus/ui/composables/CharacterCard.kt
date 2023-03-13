package com.example.rickandmortyplus.ui.composables

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.rickandmortyplus.EMPTY_CHARACTER
import com.example.rickandmortyplus.EMPTY_STR
import com.example.rickandmortyplus.R
import com.example.rickandmortyplus.RMApplication.Companion.prefs
import com.example.rickandmortyplus.STATUS_ALIVE
import com.example.rickandmortyplus.model.Character
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

    val episodeStr = if (character.episode != null) {
        if (character.episode.isNotEmpty()) {
            character.episode[0].name.toString()
        } else {
            EMPTY_STR
        }
    } else {
        EMPTY_STR
    }

    // Validating if character is favorite
    val isFavoritePrefs = prefs.getFavoriteCharacter(characterId = character.id ?: "")

    val isFavoriteColor = if (isFavoritePrefs != null) {
        ColorFilter.tint(color = MaterialTheme.colors.secondary)
    } else {
        ColorFilter.tint(color = Color.DarkGray)
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
                    .size(dimensionResource(id = R.dimen.size_image_card))
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
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_by_card_column))
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
                                .height(dimensionResource(id = R.dimen.height_circle_card))
                                .padding(end = dimensionResource(id = R.dimen.padding_card)),
                            painter = painterResource(id = R.drawable.ic_circle),
                            contentDescription = null,
                            colorFilter = colorStatus
                        )
                        Text(
                            style = MaterialTheme.typography.body2,
                            text = "${character.status} - ${character.species}"
                        )
                    }
                }

                Column() {
                    Text(
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        text = stringResource(id = R.string.last_known_location),
                    )
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = character.location?.name ?: EMPTY_STR
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray,
                            text = stringResource(id = R.string.first_seen_in)
                        )
                        Text(
                            style = MaterialTheme.typography.body2,
                            text = episodeStr
                        )
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterEnd)
                            .clickable {
                                       Log.d("Click", "clicking ")
                            },
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = null,
                        colorFilter = isFavoriteColor
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
            CharacterCard(EMPTY_CHARACTER)
        }
    }
}