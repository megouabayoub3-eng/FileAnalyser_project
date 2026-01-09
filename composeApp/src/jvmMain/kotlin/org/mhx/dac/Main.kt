package org.mhx.dac

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(800.dp, 600.dp))
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "AI-Powered File Analyzer",
        state = windowState
    ) {
        MainApp()
    }
}