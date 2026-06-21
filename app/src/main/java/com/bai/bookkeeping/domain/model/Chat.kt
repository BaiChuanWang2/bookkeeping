package com.bai.bookkeeping.domain.model

data class Chat(
    val id: String,
    val time: Long,
    val description: String,
    val amount: String,
    val category: String,
    val errorMessage: String
)
