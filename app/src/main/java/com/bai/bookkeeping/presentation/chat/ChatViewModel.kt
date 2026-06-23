package com.bai.bookkeeping.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bai.bookkeeping.domain.common.DomainError
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
import com.bai.bookkeeping.domain.common.Result
import com.bai.bookkeeping.domain.usecase.DeleteChatUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val deleteChatUseCase: DeleteChatUseCase,
    private val sendChatUseCase: SendChatUseCase
) : ViewModel() {

    private val _inputState = MutableStateFlow("")
    private val _isSendingState = MutableStateFlow(false)

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

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

                        val result = sendChatUseCase(currentInput)

                        when (result) {

                            is Result.Success -> {
                                _inputState.value = ""
                            }

                            is Result.Error -> {
                                val errorMessage = when (result.error) {
                                    is DomainError.BadRequest -> "抱歉, 我只能幫您紀錄開銷"
                                    is DomainError.Server -> "伺服器忙碌中"
                                    is DomainError.Network -> "網路異常"
                                }

                                _errorEvent.emit(errorMessage)
                            }
                        }

                        _isSendingState.value = false
                    }
                }
            }

            is ChatAction.Delete -> {
                viewModelScope.launch {
                    deleteChatUseCase(action.chat)
                }
            }

            ChatAction.Back -> Unit
        }
    }
}