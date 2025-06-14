package ru.donnu.practice.presentation.screens.home

import entity.ProductionSortedType
import entity.ProductionEntity
import entity.SortedType

data class HomeState(
    val productions: List<ProductionEntity> = emptyList(),
    val productionSortedType: ProductionSortedType = ProductionSortedType.COUNTRY,
    val sortedType: SortedType = SortedType.ASK,
)
