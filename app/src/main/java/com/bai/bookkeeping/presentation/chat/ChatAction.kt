package com.bai.bookkeeping.presentation.chat

sealed interface ChatAction {
    data object Back : ChatAction
}