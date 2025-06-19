package ru.donnu.practice.presentation.screens.delete

import entity.ProductionEntity
import entity.ProductionSortedType
import entity.SortedType

data class DeleteState(
    val deleteIds: List<Int> = emptyList(),
    val productions: List<ProductionEntity> = emptyList(),
    val productionSortedType: ProductionSortedType = ProductionSortedType.COUNTRY,
    val sortedType: SortedType = SortedType.ASK,
)
