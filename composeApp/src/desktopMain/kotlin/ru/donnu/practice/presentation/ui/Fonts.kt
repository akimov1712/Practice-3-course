package ru.donnu.practice.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.compose.resources.Font
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.extrabold
import practice_3_course.composeapp.generated.resources.medium
import practice_3_course.composeapp.generated.resources.semibold

object Fonts {

    val montserrat @Composable get() = FontFamily(
        Font(Res.font.extrabold, FontWeight.ExtraBold),
        Font(Res.font.medium, FontWeight.Medium),
        Font(Res.font.semibold, FontWeight.SemiBold),
    )

}