package ru.donnu.practice.presentation.screens.add

import entity.CountryEntity
import entity.ManufactureEntity
import entity.ManufactureType
import entity.ProductionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.donnu.practice.network.manufacture.ManufactureDTO
import ru.donnu.practice.repository.CountryRepository
import ru.donnu.practice.repository.ManufactureRepository
import ru.donnu.practice.repository.ProductionRepository

class AddViewModel{

    private val scope = CoroutineScope(Dispatchers.IO)
    private val countryRepository = CountryRepository()
    private val manufactureRepository = ManufactureRepository()

    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

    fun addProduction() = scope.launch {
        state.value.country?.let {
            val respond = listOf(
                ManufactureDTO(it.id, ManufactureType.OIL.name, state.value.oil.toDouble()),
                ManufactureDTO(it.id, ManufactureType.STEEL.name, state.value.steel.toDouble()),
                ManufactureDTO(it.id, ManufactureType.COAL.name, state.value.coal.toDouble())
            )
            manufactureRepository.addManufactures(respond)
            _state.emit(AddState())
        }
    }

    fun changeCountry(value: String) {
        val country = state.value.countryList.firstOrNull() { it.name == value }
        _state.update { it.copy(country = country) }
    }

    fun changeSteel(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(steel = value.filterChars()) }
        }
    }

    fun changeOil(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(oil = value.filterChars()) }
        }
    }

    fun changeCoal(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(coal = value.filterChars()) }
        }
    }

    private fun loadCountries() = scope.launch {
        countryRepository.getAllCountries().also { countries ->
            _state.update { it.copy(countryList = countries) }
        }
    }

    private fun String.filterChars() = this.filter { it != 'f' && it != 'd' }

    private fun String.isValidManufacture() = toDoubleOrNull() != null || this.isEmpty()

    init {
        loadCountries()
    }

    val fieldsIsValid = state.map {
        with(it){
            listOf(country, oil, coal, steel).all { it != "" && it != null }
        }
    }.stateIn(
        scope, SharingStarted.Lazily, false
    )

}
