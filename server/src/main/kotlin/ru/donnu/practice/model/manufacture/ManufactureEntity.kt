package ru.donnu.practice.model.manufacture

import kotlinx.serialization.Serializable

@Serializable
data class ManufactureEntity(
    val id: Int,
    val countryId: Int,
    val type: ManufactureType,
    val value: Double,
)
