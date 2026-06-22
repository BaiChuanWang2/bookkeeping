package com.bai.bookkeeping.data.repository

import com.bai.bookkeeping.data.local.dao.ChatDao
import com.bai.bookkeeping.data.mapper.createErrorChat
import com.bai.bookkeeping.data.mapper.toDomain
import com.bai.bookkeeping.data.mapper.toEntity
import com.bai.bookkeeping.data.remote.api.ChatApi
import com.bai.bookkeeping.domain.model.Chat
import com.bai.bookkeeping.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatApi: ChatApi,
    private val chatDao: ChatDao
) : ChatRepository {

    override fun getChat(): Flow<List<Chat>> {
        return chatDao.getAllChat().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveChat(chat: Chat) {
        val entity = chat.toEntity()
        chatDao.insertChat(entity)
    }

    override suspend fun sendChat(message: String): Chat {
        val response = chatApi.chat(message = message)

        when (response.code()) {
            200 -> {
                val body = response.body()

                return body!!.toDomain()
            }
            else -> {
                val errorMessage = response.errorBody()!!.string()

                return createErrorChat(errorMessage = errorMessage)
            }
        }
    }
}