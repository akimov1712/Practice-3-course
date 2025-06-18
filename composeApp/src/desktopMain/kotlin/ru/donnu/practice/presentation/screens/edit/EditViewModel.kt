package ru.donnu.practice.presentation.screens.edit

import entity.CountryEntity
import entity.ManufactureType
import entity.ProductionSortedType
import entity.SortedType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.donnu.practice.repository.CountryRepository
import ru.donnu.practice.repository.ProductionRepository
import ru.donnu.practice.utills.filterChars
import ru.donnu.practice.utills.isValidManufacture
import ru.donnu.practice.utills.sortedWithType

class EditViewModel {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val productionRepository = ProductionRepository()

    private val _state = MutableStateFlow(EditState())
    val state = _state.asStateFlow()

    fun changeValueField(country: CountryEntity, type: ManufactureType, value: String){
        if (!value.isValidManufacture()) return
        _state.update {
            val oldProduct = it.productions.first{it.country == country}
            val oldManufacts = oldProduct.manufacturers
            val newManufacts = when(type){
                ManufactureType.STEEL -> oldManufacts.map { it.copy(value = value.filterChars().toDouble()).takeIf { it.type == ManufactureType.STEEL} ?: it }
                ManufactureType.COAL -> oldManufacts.map { it.copy(value = value.filterChars().toDouble()).takeIf { it.type == ManufactureType.COAL} ?: it }
                ManufactureType.OIL -> oldManufacts.map { it.copy(value = value.filterChars().toDouble()).takeIf { it.type == ManufactureType.OIL} ?: it }
            }
            val newProductions = it.productions.map {
                if (it.country == country) oldProduct.copy(manufacturers = newManufacts) else it
            }
            it.copy(productions = newProductions)
        }

    }

    fun changeEditMode() = _state.update { it.copy(editMode = !it.editMode) }

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