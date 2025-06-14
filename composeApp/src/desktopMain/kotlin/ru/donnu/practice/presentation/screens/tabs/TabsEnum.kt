package ru.donnu.practice.presentation.screens.tabs

import androidx.annotation.DrawableRes
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.skia.Drawable
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_tabs_add
import practice_3_course.composeapp.generated.resources.ic_tabs_delete
import practice_3_course.composeapp.generated.resources.ic_tabs_edit
import practice_3_course.composeapp.generated.resources.ic_tabs_home

enum class TabsEnum(val text: String, val icon: DrawableResource) {

    Home(
        text = "Главная",
        icon = Res.drawable.ic_tabs_home
    ),

    Add(
        text = "Добавить",
        icon = Res.drawable.ic_tabs_add
    ),

    Edit(
        text = "Редактировать",
        icon = Res.drawable.ic_tabs_edit
    ),

    Delete(
        text = "Удалить",
        icon = Res.drawable.ic_tabs_delete
    )

}