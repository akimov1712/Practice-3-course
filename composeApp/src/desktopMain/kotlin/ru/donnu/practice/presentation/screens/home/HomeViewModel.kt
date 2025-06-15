package ru.donnu.practice.presentation.screens.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import entity.ProductionSortedType
import entity.SortedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.donnu.practice.repository.ProductionRepository
import ru.donnu.practice.utills.sortedWithType

class HomeViewModel {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val productionRepository = ProductionRepository()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    val sortedProduction = state.map {
        it.productions.sortedWithType(it.productionSortedType, it.sortedType)
    }.stateIn(
        scope, SharingStarted.Lazily, state.value.productions
    )

    fun changeSortedType(type: ProductionSortedType){
        if (type == _state.value.productionSortedType){
            _state.update { state ->
                val newType = SortedType.DESK.takeIf { SortedType.ASK == state.sortedType } ?: SortedType.ASK
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