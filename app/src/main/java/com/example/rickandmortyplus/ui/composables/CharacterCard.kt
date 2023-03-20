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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.rickandmortyplus.*
import com.example.rickandmortyplus.R
import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.ui.theme.RickAndMortyPlusTheme
import com.example.rickandmortyplus.viewmodel.CharacterViewModel

@Composable
fun CharacterCard(
    character: Character,
    setFavorite: (character: Character) -> Unit,
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
    val isFavoriteCharacter =
        RMApplication.prefs.getFavoriteCharacter(characterId = character.id.toString())

    val isFavoriteColor = if (isFavoriteCharacter != null) {
        ColorFilter.tint(color = MaterialTheme.colors.secondary)
    } else {
        ColorFilter.tint(color = Color.DarkGray)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.space_by_card_column))
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.size_image_card)),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    model = character.image,
                    contentDescription = null,
                ) {

                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.16f)
                    .padding(dimensionResource(id = R.dimen.padding_card)),

                ) {
                Column {
                    Text(
                        style = MaterialTheme.typography.h6,
                        text = character.name ?: EMPTY_STR,
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

                Column {
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier,
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
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Image(
                            modifier = Modifier
                                .clickable {
                                    // RMApplication.prefs.saveFavoriteCharacter(characterId = character.id.toString())
                                    // characterViewModel.saveFavoriteCharacter(idCharacter = character.id ?: EMPTY_STR)
                                    // characterViewModel.updateFavoriteCharacter(character)
                                    setFavorite(character)
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
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCharacterCard() {
    RickAndMortyPlusTheme() {
        Surface() {
            CharacterCard(EMPTY_CHARACTER, hiltViewModel())
        }
    }
}