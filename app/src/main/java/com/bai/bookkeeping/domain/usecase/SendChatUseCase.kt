package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.repository.ChatRepository
import javax.inject.Inject

class SendChatUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val saveChatUseCase: SaveChatUseCase
) {
    suspend operator fun invoke(message: String) {

        val chat = repository.sendChat(message)

        saveChatUseCase(chat)
    }
}