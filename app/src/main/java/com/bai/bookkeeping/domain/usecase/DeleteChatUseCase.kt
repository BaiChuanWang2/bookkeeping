package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.model.Chat
import com.bai.bookkeeping.domain.repository.ChatRepository
import javax.inject.Inject

class DeleteChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chat: Chat) {

        repository.deleteChat(chat)
    }
}