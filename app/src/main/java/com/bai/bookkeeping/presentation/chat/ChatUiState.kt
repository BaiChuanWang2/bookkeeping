package com.bai.bookkeeping.presentation.chat

import com.bai.bookkeeping.domain.model.Chat

sealed interface ChatUiState {
    data class Content(
        val messages: List<Chat> = emptyList(),
        val input: String = "",
        val isSending: Boolean = false
    ) : ChatUiState
}