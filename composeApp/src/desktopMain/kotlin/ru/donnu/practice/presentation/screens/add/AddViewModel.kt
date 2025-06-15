package ru.donnu.practice.presentation.screens.add

import entity.CountryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.donnu.practice.repository.CountryRepository

class AddViewModel{

    private val scope = CoroutineScope(Dispatchers.IO)
    private val countryRepository = CountryRepository()

    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

    fun changeCountry(country: CountryEntity) {
        _state.update { it.copy(country = country) }
    }

    fun changeSteel(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(steel = value) }
        }
    }

    fun changeOil(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(oil = value) }
        }
    }

    fun changeCoal(value: String){
        if (value.isValidManufacture()){
            _state.update { it.copy(coal = value) }
        }
    }

    private fun loadCountries() = scope.launch {
        countryRepository.getAllCountries().also { countries ->
            _state.update { it.copy(countryList = countries) }
        }
    }

    private fun String.isValidManufacture() = toDoubleOrNull() != null

    init {
        loadCountries()
    }

}
