package com.bai.bookkeeping.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    onAction: (ChatAction) -> Unit
) {
    when (uiState) {

        ChatUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ChatUiState.Content -> {
            Column(
                modifier = Modifier.statusBarsPadding()
            ) {

                TextField(
                    value = uiState.input,
                    onValueChange = {
                        onAction(ChatAction.InputChanged(it))
                    }
                )

                Button(
                    onClick = {
                        onAction(ChatAction.Send)
                    }
                ) {
                    Text("Send")
                }

                Button(
                    onClick = {
                        onAction(ChatAction.Back)
                    }
                ) {
                    Text("Back Main")
                }

                LazyColumn {
                    items(uiState.messages) {
                        Text(it)
                    }
                }
            }
        }
    }
}