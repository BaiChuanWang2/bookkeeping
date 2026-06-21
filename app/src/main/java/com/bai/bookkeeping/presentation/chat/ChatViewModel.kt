package com.bai.bookkeeping.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bai.bookkeeping.domain.usecase.GetChatUseCase
import com.bai.bookkeeping.domain.usecase.SendChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val sendChatUseCase: SendChatUseCase
) : ViewModel() {

    private val _inputState = MutableStateFlow("")

    val uiState: StateFlow<ChatUiState> = combine(
        getChatUseCase(),
        _inputState
    ) { chatList, currentInput ->
        ChatUiState.Content(
            messages = chatList,
            input = currentInput
        )
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChatUiState.Loading
    )

    fun onAction(action: ChatAction) {
        when (action) {
            is ChatAction.InputChanged -> {
                _inputState.value = action.value
            }

            ChatAction.Send -> {
                val currentInput = _inputState.value
                if (currentInput.isNotBlank()) {
                    viewModelScope.launch {
                        _inputState.value = ""

                        sendChatUseCase(currentInput)
                    }
                }
            }

            ChatAction.Back -> Unit
        }
    }
}