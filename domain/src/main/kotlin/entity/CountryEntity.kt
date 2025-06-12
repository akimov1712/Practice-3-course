package entity

import kotlinx.serialization.Serializable

@Serializable
data class CountryEntity(
    val id: Int,
    val name: String,
    val region: RegionEntity,
    val image: String
)