package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.model.Chat
import com.bai.bookkeeping.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<List<Chat>> {

        return repository.getChat()
    }
}