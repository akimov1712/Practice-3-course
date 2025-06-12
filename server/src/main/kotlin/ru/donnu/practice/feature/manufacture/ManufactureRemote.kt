package ru.donnu.practice.feature.manufacture

import kotlinx.serialization.Serializable

@Serializable
data class ManufactureReceive(
    val countryId: Int,
    val type: String,
    val value: Double,
)