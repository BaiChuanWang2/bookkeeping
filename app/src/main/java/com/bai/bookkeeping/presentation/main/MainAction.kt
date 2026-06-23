package com.bai.bookkeeping.presentation.main

sealed interface MainAction {
    data object Chat : MainAction
}