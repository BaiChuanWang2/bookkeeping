package com.bai.bookkeeping.presentation.chat

sealed interface ChatAction {
    data object Back : ChatAction
    data class InputChanged(val value: String) : ChatAction
    data object Send : ChatAction
}