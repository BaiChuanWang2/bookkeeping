package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.model.Chat
import com.bai.bookkeeping.domain.repository.ChatRepository
import javax.inject.Inject

class SaveChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chat: Chat) {

        repository.saveChat(chat)
    }
}