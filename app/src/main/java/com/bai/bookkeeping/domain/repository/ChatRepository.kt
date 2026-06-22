package com.bai.bookkeeping.domain.repository

import com.bai.bookkeeping.domain.model.Chat
import kotlinx.coroutines.flow.Flow
import com.bai.bookkeeping.domain.common.Result

interface ChatRepository {
    fun getChat(): Flow<List<Chat>>

    suspend fun saveChat(chat: Chat)

    suspend fun sendChat(message: String): Result<Chat>
}