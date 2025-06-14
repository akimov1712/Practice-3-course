package ru.donnu.practice.presentation.screens.tabs

data class TabsState(
    val tabs: List<TabsEnum> = TabsEnum.entries,
    val selectedIndex: Int = 0
)
