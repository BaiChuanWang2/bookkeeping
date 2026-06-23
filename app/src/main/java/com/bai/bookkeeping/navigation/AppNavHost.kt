package com.bai.bookkeeping.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.bai.bookkeeping.presentation.chat.ChatRoute
import com.bai.bookkeeping.presentation.main.MainRoute

@Composable
fun AppNavHost() {
    val backStack = rememberNavBackStack(
        Screen.Main
    )

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {

            entry<Screen.Main> {
                MainRoute(
                    onChat = {
                        backStack.add(Screen.Chat)
                    }
                )
            }

            entry<Screen.Chat> {
                ChatRoute(
                    onBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}