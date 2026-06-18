package com.bai.bookkeeping.data.mapper

import com.bai.bookkeeping.data.local.entity.ChatEntity
import com.bai.bookkeeping.domain.model.Chat

// DB Entity → Domain Model
fun ChatEntity.toDomain(): Chat {
    return Chat(
        id = this.id,
        message = this.message,
        time = this.time
    )
}

// Domain Model → DB Entity
fun Chat.toEntity(): ChatEntity {
    return ChatEntity(
        id = this.id,
        message = this.message,
        time = this.time
    )
}