package com.bai.bookkeeping.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    onAction: (ChatAction) -> Unit
) {
    when (uiState) {

        is ChatUiState.Content -> {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp)
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.input,
                    onValueChange = {
                        val replaceData = it.replace(Regex("[\\n\\r]"), "")
                        onAction(ChatAction.InputChanged(replaceData))
                    },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            onAction(ChatAction.Send)
                        }
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAction(ChatAction.Send)
                            },
                            enabled = !uiState.isSending
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send"
                            )
                        }
                    }
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    items(uiState.messages) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorScheme.surfaceVariant)
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                it.category,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                it.description,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                it.amount,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}