package com.bai.bookkeeping.domain.common

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: DomainError) : Result<Nothing>()
}