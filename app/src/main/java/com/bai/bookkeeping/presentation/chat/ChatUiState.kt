package com.bai.bookkeeping.presentation.chat

sealed interface ChatUiState {
    data object Loading : ChatUiState
    data class Content(val messages: List<String> = emptyList(), val input: String = "") : ChatUiState
}