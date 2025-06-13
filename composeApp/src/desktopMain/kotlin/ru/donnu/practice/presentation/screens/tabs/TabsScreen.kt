package ru.donnu.practice.presentation.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.skia.ColorChannel
import practice_3_course.composeapp.generated.resources.Res
import ru.donnu.practice.presentation.ui.Colors

@Composable
fun TabsScreen() {
    Row(
        modifier = Modifier.fillMaxSize()
    ){
        AsideNavigation()
    }
}

@Composable
private fun AsideNavigation(){
    Column(
        modifier = Modifier.fillMaxHeight()
            .background(Colors.BG_BLUE)
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Icon(
                painter = painterResource()
            )
        }
    }
}