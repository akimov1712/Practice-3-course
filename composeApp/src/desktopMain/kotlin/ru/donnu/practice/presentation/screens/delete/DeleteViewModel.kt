package ru.donnu.practice.presentation.screens.delete

import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.state.ToggleableState.*
import entity.ProductionSortedType
import entity.SortedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.donnu.practice.repository.ManufactureRepository
import ru.donnu.practice.repository.ProductionRepository
import ru.donnu.practice.utills.sortedWithType

class DeleteViewModel {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val productionRepository = ProductionRepository()
    private val manufactRepository = ManufactureRepository()

    private val _state = MutableStateFlow(DeleteState())
    val state = _state.asStateFlow()

    val sortedProduction = state.map {
        it.productions.sortedWithType(it.productionSortedType, it.sortedType)
    }.stateIn(
        scope, SharingStarted.Lazily, state.value.productions
    )

    val generalCondition = state.map {
        when{
            state.value.deleteIds.isEmpty() -> Off
            state.value.deleteIds.size == state.value.productions.size -> On
            else -> Indeterminate
        }
    }.stateIn(
        scope, SharingStarted.Lazily, Off
    )

    fun deleteProduction() = scope.launch {
        state.value.deleteIds.forEach { manufactRepository.deleteManufacture(it) }
        val newList = state.value.productions.filter { !state.value.deleteIds.contains(it.country.id) }
        _state.update { it.copy(productions = newList, deleteIds = emptyList()) }
    }

    fun toggleGeneralCondition() = scope.launch{
        val condition = generalCondition.first()
        val newList = if(condition == On) listOf() else state.value.productions.map { it.country.id }
        _state.update { it.copy(deleteIds = newList) }
    }

    fun toggleDeleteId(countryId: Int){
        val newList = state.value.deleteIds.toMutableList().apply {
            if (contains(countryId)) remove(countryId) else add(countryId)
        }
        _state.update { it.copy(newList) }
    }

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