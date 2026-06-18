package com.bai.bookkeeping.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bai.bookkeeping.domain.model.Chat
import com.bai.bookkeeping.domain.usecase.GetChatUseCase
import com.bai.bookkeeping.domain.usecase.SaveChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val saveChatUseCase: SaveChatUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    init {
        initChat()
    }

    private fun initChat() {
        viewModelScope.launch {
            getChatUseCase().collect { chatList ->
                _uiState.update { currentState ->
                    val currentInput = if (currentState is ChatUiState.Content) {
                        currentState.input
                    } else {
                        ""
                    }

                    ChatUiState.Content(
                        messages = chatList.map { it.message },
                        input = currentInput
                    )
                }
            }
        }
    }

    fun onAction(action: ChatAction) {
        when (action) {

            is ChatAction.InputChanged -> {
                val state = _uiState.value
                if (state is ChatUiState.Content) {
                    _uiState.value = state.copy(input = action.value)
                }
            }

            ChatAction.Send -> {
                val state = _uiState.value
                if (state is ChatUiState.Content && state.input.isNotBlank()) {
                    viewModelScope.launch {
                        val currentInput = state.input

                        _uiState.value = state.copy(input = "")

                        val newChat = Chat(
                            id = UUID.randomUUID().toString(),
                            message = currentInput,
                            time = System.currentTimeMillis()
                        )
                        saveChatUseCase(newChat)
                    }
                }
            }

            ChatAction.Back -> Unit
        }
    }
}