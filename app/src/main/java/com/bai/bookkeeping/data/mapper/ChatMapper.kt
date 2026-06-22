package com.bai.bookkeeping.data.mapper

import com.bai.bookkeeping.data.local.entity.ChatEntity
import com.bai.bookkeeping.data.remote.response.ChatResponse
import com.bai.bookkeeping.domain.model.Chat
import java.util.UUID

fun ChatEntity.toDomain(): Chat {
    return Chat(
        id = this.id,
        time = this.time,
        description = this.description,
        amount = this.amount,
        category = this.category,
        errorMessage = this.errorMessage
    )
}

fun Chat.toEntity(): ChatEntity {
    return ChatEntity(
        id = this.id,
        time = this.time,
        description = this.description,
        amount = this.amount,
        category = this.category,
        errorMessage = this.errorMessage
    )
}

fun ChatResponse.toDomain(): Chat {
    return Chat(
        id = this.id ?: UUID.randomUUID().toString(),
        time = System.currentTimeMillis(),
        description = this.description ?: "",
        amount = this.amount ?: "",
        category = this.category ?: "",
        errorMessage = this.errorMessage ?: ""
    )
}

fun createErrorChat(errorMessage: String): Chat {
    return Chat(
        id = UUID.randomUUID().toString(),
        time = System.currentTimeMillis(),
        description = "",
        amount = "",
        category = "",
        errorMessage = errorMessage
    )
}