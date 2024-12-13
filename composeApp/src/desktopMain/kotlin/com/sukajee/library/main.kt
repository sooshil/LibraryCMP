package com.sukajee.library

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sukajee.library.di.initKoin

fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Library",
        ) {
            App()
        }
    }
}