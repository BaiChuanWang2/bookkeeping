package com.bai.bookkeeping.data.repository

import com.bai.bookkeeping.data.local.dao.ChatDao
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

    // 從 Room 讀取資料並轉成 Domain Model 丟給 UseCase
    override fun getChat(): Flow<List<Chat>> {
        return chatDao.getAllChat().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    // 儲存資料到 Room，並可同時發送到遠端 API
    override suspend fun saveChat(chat: Chat) {
        // 1. 存入本地 DB
        chatDao.insertChat(chat.toEntity())

        // 2. 呼叫 API 同步到伺服器
//        chatApi.chat(
//            userId = "0",
//            message = chat.message
//        )
    }
}