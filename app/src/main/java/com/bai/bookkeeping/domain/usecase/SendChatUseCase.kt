package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.repository.ChatRepository
import javax.inject.Inject
import com.bai.bookkeeping.domain.common.Result
import com.bai.bookkeeping.domain.model.Chat

class SendChatUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val saveChatUseCase: SaveChatUseCase
) {
    suspend operator fun invoke(message: String): Result<List<Chat>> {

        val chat = repository.sendChat(message)

        when (chat) {

            is Result.Success -> {
                chat.data.forEach {
                    saveChatUseCase(it)
                }
            }

            is Result.Error -> {

            }
        }
        return chat
    }
}