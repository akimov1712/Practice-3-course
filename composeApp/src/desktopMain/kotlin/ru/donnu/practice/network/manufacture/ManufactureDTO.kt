package ru.donnu.practice.network.manufacture

import kotlinx.serialization.Serializable

@Serializable
data class ManufactureDTO(
    val countryId: Int,
    val type: String,
    val value: Double,
)
