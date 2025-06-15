package ru.donnu.practice.presentation.screens.add

import entity.CountryEntity

data class AddState(
    val countryList: List<CountryEntity> = emptyList(),
    val country: CountryEntity? = null,
    val steel: String = "",
    val coal: String = "",
    val oil: String = "",
)
