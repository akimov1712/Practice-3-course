package ru.donnu.practice.model.country

import kotlinx.serialization.Serializable
import ru.donnu.practice.model.region.RegionEntity
import ru.donnu.practice.model.region.RegionEnum

@Serializable
data class CountryEntity(
    val id: Int,
    val name: String,
    val region: RegionEnum,
    val image: String
)