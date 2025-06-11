package ru.donnu.practice.model.production

import kotlinx.serialization.Serializable
import ru.donnu.practice.model.country.CountryEntity
import ru.donnu.practice.model.manufacture.ManufactureEntity

@Serializable
data class ProductionEntity(
    val country: CountryEntity,
    val manufacturers: List<ManufactureEntity>,
)
