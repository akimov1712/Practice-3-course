package ru.donnu.practice.presentation.screens.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import entity.ProductionSortedType
import entity.SortedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.donnu.practice.repository.ProductionRepository

class HomeViewModel {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val productionRepository = ProductionRepository()

    fun changeSortedType(type: ProductionSortedType){
        if (type == _state.value.productionSortedType){
            _state.update { state ->
                val newType = SortedType.DESK.takeIf { SortedType.ASK == state.sortedType } ?: SortedType.DESK
                state.copy(sortedType = newType)
            }
        } else{
            _state.update { state ->
                state.copy(productionSortedType = type, sortedType = SortedType.ASK)
            }
        }
    }

    private fun loadProductions() = scope.launch {
        val result = productionRepository.getAllProduction()
        _state.update { it.copy(productions = result) }
    }

    init {
        loadProductions()
    }

}