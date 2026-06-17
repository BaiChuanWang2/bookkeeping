package com.bai.bookkeeping.presentation.chat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<ChatUiState>(
        ChatUiState.Loading
    )

    val uiState: StateFlow<ChatUiState> = _uiState
}