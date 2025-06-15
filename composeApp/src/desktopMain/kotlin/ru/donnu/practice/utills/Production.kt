package ru.donnu.practice.utills

import entity.ManufactureType
import entity.ProductionEntity
import entity.ProductionSortedType
import entity.ProductionSortedType.*
import entity.SortedType

private fun ProductionEntity.getSortedFieldWithType(productionSortedType: ProductionSortedType) = when (productionSortedType) {
    COUNTRY -> country.name
    REGION -> country.region.name
    STEEL -> manufacturers.first { it.type == ManufactureType.STEEL }.value
    OIL -> manufacturers.first { it.type == ManufactureType.OIL }.value
    COAL -> manufacturers.first { it.type == ManufactureType.COAL }.value
}

fun List<ProductionEntity>.sortedWithType(
    productionSortedType: ProductionSortedType,
    type: SortedType
): List<ProductionEntity> {
    return when (productionSortedType) {
        COUNTRY, REGION -> {
            val selector: (ProductionEntity) -> String = when (productionSortedType) {
                COUNTRY -> { it -> it.country.name }
                REGION -> { it -> it.country.region.name }
                else -> throw IllegalArgumentException("Unexpected type")
            }
            if (type == SortedType.ASK) sortedBy(selector) else sortedByDescending(selector)
        }
        STEEL, OIL, COAL -> {
            val selector: (ProductionEntity) -> Double = when (productionSortedType) {
                STEEL -> { it -> it.manufacturers.first { m -> m.type == ManufactureType.STEEL }.value }
                OIL -> { it -> it.manufacturers.first { m -> m.type == ManufactureType.OIL }.value }
                COAL -> { it -> it.manufacturers.first { m -> m.type == ManufactureType.COAL }.value }
                else -> throw IllegalArgumentException("Unexpected type")
            }
            if (type == SortedType.ASK) sortedBy(selector) else sortedByDescending(selector)
        }
    }
}