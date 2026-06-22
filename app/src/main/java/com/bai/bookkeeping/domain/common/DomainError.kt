package com.bai.bookkeeping.domain.common

sealed class DomainError {
    data object Server : DomainError()
    data object Network : DomainError()
}