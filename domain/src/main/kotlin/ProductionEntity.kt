package entity

import kotlinx.serialization.Serializable

@Serializable
data class ProductionEntity(
    val country: CountryEntity,
    val manufacturers: List<ManufactureEntity>,
)
