package ru.donnu.practice.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ru.donnu.practice.presentation.screens.tabs.TabsScreen
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.Fonts

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Donnu Practice",
        resizable = false,
        state = WindowState(size = WindowSize(1250.dp, 800.dp))
    ) {
        TabsScreen()
    }
}