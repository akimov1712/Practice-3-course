package ru.donnu.practice.feature.manufacture

import kotlinx.serialization.Serializable
import ru.donnu.practice.model.manufacture.ManufactureType

@Serializable
data class ManufactureReceive(
    val countryId: Int,
    val type: String,
    val value: Double,
)