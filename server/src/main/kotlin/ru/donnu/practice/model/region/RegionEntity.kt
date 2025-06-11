package ru.donnu.practice.model.region

import kotlinx.serialization.Serializable

@Serializable
data class RegionEntity (
    val id: Int,
    val name: String
)
