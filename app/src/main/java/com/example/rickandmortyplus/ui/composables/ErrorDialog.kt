package com.example.rickandmortyplus.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.example.rickandmortyplus.R

@Composable
fun ErrorDialog(
    message: String,
    onErrorDialogDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.semantics { testTag = "error-dialog" },
        onDismissRequest = { },
        title = {
            Text(text = stringResource(R.string.error_dialog_title))
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(onClick = { onErrorDialogDismiss() }) {
                Text(text = stringResource(R.string.try_again))
            }
        })
}