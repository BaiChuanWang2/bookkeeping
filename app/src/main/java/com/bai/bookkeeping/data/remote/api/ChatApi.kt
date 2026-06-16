package com.bai.bookkeeping.data.remote.api

import com.bai.bookkeeping.data.remote.response.ChatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatApi {
    @GET("chat")
    suspend fun chat(
        @Query("user_id") userId: String,
        @Query("message") message: String,
    ) : ChatResponse
}