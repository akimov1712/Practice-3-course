package ru.donnu.practice.presentation.screens.tabs

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TabsViewModel {

    private val _state = MutableStateFlow(TabsState())
    val state get() = _state.asStateFlow()

    fun changeSelectedTabs(index: Int){
        _state.update { it.copy(selectedIndex = index) }
    }

}