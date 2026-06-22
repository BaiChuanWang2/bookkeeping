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
    private val _isSendingState = MutableStateFlow(false)

    val uiState: StateFlow<ChatUiState> = combine(
        getChatUseCase(),
        _inputState,
        _isSendingState
    ) { chatList, currentInput, isSending ->
        ChatUiState.Content(
            messages = chatList,
            input = currentInput,
            isSending = isSending
        )
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChatUiState.Content()
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

                        _isSendingState.value = true

                        _inputState.value = ""

                        sendChatUseCase(currentInput)

                        _isSendingState.value = false
                    }
                }
            }

            ChatAction.Back -> Unit
        }
    }
}