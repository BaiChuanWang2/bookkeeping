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

    init {
        _uiState.value = ChatUiState.Content()
    }

    fun onAction(action: ChatAction) {
        when (action) {

            is ChatAction.InputChanged -> {
                val state = _uiState.value
                if (state is ChatUiState.Content) {
                    _uiState.value = state.copy(
                        input = action.value
                    )
                }
            }

            ChatAction.Send -> {
                val state = _uiState.value
                if (state is ChatUiState.Content) {
                    _uiState.value = state.copy(
                        messages = state.messages + state.input,
                        input = ""
                    )
                }
            }

            ChatAction.Back -> Unit
        }
    }
}