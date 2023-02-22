package com.example.rickandmortyplus.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.rickandmortyplus.R

@Composable
fun CharacterCard() {
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
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                alignment = Alignment.Center,
                model = "https://rickandmortyapi.com/api/character/avatar/109.jpeg",
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
                    }
            ) {

                Column() {
                    Text(
                        text = "Duck With Muscles",
                        style = TextStyle(
                            fontSize = 22.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.height(10.dp),
                            painter = painterResource(id = R.drawable.ic_circle),
                            contentDescription = "status",
                            colorFilter = ColorFilter.tint(color = Color.Red)
                        )
                        Text(text = "Dead - Alien")
                    }
                }

                Column() {
                    Text(text = "Las known location:")
                    Text(text = "Earth (Replacement Dimension)")
                }

                Column() {
                    Text(text = "First seen in:")
                    Text(text = "Total Rickall")
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCard() {
    CharacterCard()
}