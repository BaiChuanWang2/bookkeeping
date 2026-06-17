package com.bai.bookkeeping.domain.usecase

import com.bai.bookkeeping.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke() {

    }
}