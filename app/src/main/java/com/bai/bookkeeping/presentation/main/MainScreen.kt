package com.bai.bookkeeping.presentation.main

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    uiState: MainUiState,
    onAction: (MainAction) -> Unit
) {
    when (uiState) {

        is MainUiState.Content -> {
            Button(
                modifier = Modifier.statusBarsPadding(),
                onClick = {
                    onAction(MainAction.OpenChat)
                }
            ) {
                Text("Open Chat")
            }
        }
    }
}