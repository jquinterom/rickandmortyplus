package com.example.rickandmortyplus.ui.composables

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharacterCard() {
    Card(
        modifier = Modifier.shadow(dimensionResource(id = ))
    ) {
        Text(text = "Character card")
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCard() {
    CharacterCard()
}