package ru.donnu.practice.presentation.screens.home

import entity.DoubleRange
import entity.ProductionSortedType
import entity.ProductionEntity
import entity.SortedType

data class HomeState(
    val productions: List<ProductionEntity> = emptyList(),
    val productionSortedType: ProductionSortedType = ProductionSortedType.COUNTRY,
    val sortedType: SortedType = SortedType.ASK,
    val coerceCoal: DoubleRange = DoubleRange.EMPTY,
    val coerceOil: DoubleRange = DoubleRange.EMPTY,
    val coerceSteel: DoubleRange = DoubleRange.EMPTY,
    val showDialog: Boolean = false,
)
