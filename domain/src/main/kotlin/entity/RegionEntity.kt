package entity

import kotlinx.serialization.Serializable

@Serializable
data class RegionEntity (
    val id: Int,
    val name: String
)
