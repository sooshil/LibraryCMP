package com.sukajee.library

import androidx.compose.ui.window.ComposeUIViewController
import com.sukajee.library.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}