package ru.donnu.practice.presentation.screens.edit

import entity.CountryEntity
import entity.ProductionEntity
import entity.ProductionSortedType
import entity.SortedType

data class EditState(
    val editMode: Boolean = false,
    val productions: List<ProductionEntity> = emptyList(),
    val productionSortedType: ProductionSortedType = ProductionSortedType.COUNTRY,
    val sortedType: SortedType = SortedType.ASK,
)
