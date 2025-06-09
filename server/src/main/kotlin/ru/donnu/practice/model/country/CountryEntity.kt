package ru.donnu.practice.model.country

import kotlinx.serialization.Serializable
import ru.donnu.practice.model.region.RegionEntity

@Serializable
data class CountryEntity(
    val id: Int,
    val name: String,
    val regionId: Int,
    val image: String
)