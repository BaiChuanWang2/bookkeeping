package com.bai.bookkeeping.presentation.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    uiState: MainUiState,
    onAction: (MainAction) -> Unit
) {
    when (uiState) {

        is MainUiState.Content -> {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(16.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    onAction(MainAction.Chat)
                }
            ) {
                Text("Chat")
            }
        }
    }
}