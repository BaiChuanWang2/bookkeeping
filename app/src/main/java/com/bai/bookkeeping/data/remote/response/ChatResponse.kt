package com.bai.bookkeeping.data.remote.response

data class ChatResponse(
    val id: String,
    val time: Long,
    val description: String,
    val amount: String,
    val category: String,
    val errorMessage: String
)
