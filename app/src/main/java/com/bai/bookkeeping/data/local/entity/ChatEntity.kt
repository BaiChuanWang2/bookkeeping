package com.bai.bookkeeping.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class ChatEntity(
    @PrimaryKey val id: String,
    val time: Long,
    val description: String,
    val amount: String,
    val category: String,
)