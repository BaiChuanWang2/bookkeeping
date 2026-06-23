package com.bai.bookkeeping.presentation.chat

import com.bai.bookkeeping.domain.model.Chat

sealed interface ChatAction {
    data object Back : ChatAction
    data class InputChanged(val value: String) : ChatAction
    data object Send : ChatAction
    data class Delete(val chat: Chat) : ChatAction
}