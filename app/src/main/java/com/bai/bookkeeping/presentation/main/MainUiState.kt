package com.bai.bookkeeping.presentation.main

sealed interface MainUiState {
    data object Content : MainUiState
}