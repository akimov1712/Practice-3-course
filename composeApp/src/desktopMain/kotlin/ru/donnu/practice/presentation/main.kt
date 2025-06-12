package ru.donnu.practice.presentation

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Practice-3-course",
    ) {
    }
}