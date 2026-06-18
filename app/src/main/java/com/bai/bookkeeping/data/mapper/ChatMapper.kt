package com.bai.bookkeeping.data.mapper

import com.bai.bookkeeping.data.local.entity.ChatEntity
import com.bai.bookkeeping.domain.model.Chat

// 將 DB Entity 轉為 Domain Model
fun ChatEntity.toDomain(): Chat {
    return Chat(
        id = this.id,
        message = this.message,
        time = this.time
    )
}

// 將 Domain Model 轉為 DB Entity
fun Chat.toEntity(): ChatEntity {
    return ChatEntity(
        id = this.id,
        message = this.message,
        time = this.time
    )
}