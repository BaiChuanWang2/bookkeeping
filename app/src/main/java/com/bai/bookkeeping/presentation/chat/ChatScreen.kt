package com.bai.bookkeeping.presentation.chat

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    onAction: (ChatAction) -> Unit
) {
    Button(
        modifier = Modifier.statusBarsPadding(),
        onClick = {
            onAction(ChatAction.Back)
        }
    ) {
        Text("Back Main")
    }
}