package ru.donnu.practice.utills

import entity.ManufactureType
import entity.ProductionEntity
import entity.ProductionSortedType
import entity.ProductionSortedType.*
import entity.SortedType

private fun ProductionEntity.getSortedFieldWithType(productionSortedType: ProductionSortedType) = when (productionSortedType) {
    COUNTRY -> country.name
    REGION -> country.region.name
    STEEL -> manufacturers.first { it.type == ManufactureType.STEEL }.value.toString()
    OIL -> manufacturers.first { it.type == ManufactureType.OIL }.value.toString()
    COAL -> manufacturers.first { it.type == ManufactureType.COAL }.value.toString()
}

fun List<ProductionEntity>.sortedWithType(
    productionSortedType: ProductionSortedType,
    type: SortedType
):List<ProductionEntity>{
    return if (type == SortedType.ASK){ sortedBy { it.getSortedFieldWithType(productionSortedType) }
    } else { sortedByDescending { it.getSortedFieldWithType(productionSortedType) } }
}