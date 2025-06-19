package ru.donnu.practice.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.*
import org.jetbrains.compose.resources.painterResource
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_dropdown
import ru.donnu.practice.presentation.ui.Colors

@Composable
fun TitleDeleteProductionRow(
    state: ToggleableState,
    productionSortedType: ProductionSortedType,
    sortedType: SortedType,
    onClickCheckBox: () -> Unit = {},
    onClickItem: (ProductionSortedType) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xff4B90F7).copy(0.15f), RoundedCornerShape(8.dp))
            .padding(28.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TriStateCheckbox(
            state = state,
            onClick = onClickCheckBox,
            modifier = Modifier.size(16.dp),
            colors = CheckboxDefaults.colors(
                Colors.BLUE,Colors.BLUE,
            )
        )
        Spacer(Modifier.width(18.dp))
        Row(
            Modifier.weight(1f)
        ) {
            ProductionItem(
                text = "Страна",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COUNTRY},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.COUNTRY) }
            ProductionItem(
                text = "Регион",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.REGION},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.REGION) }
        }
        Row(
            Modifier.weight(1f)
        ) {
            ProductionItem(
                text = "Сталь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.STEEL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.STEEL) }
            ProductionItem(
                text = "Уголь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COAL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.COAL) }
            ProductionItem(
                text = "Нефть",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.OIL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.OIL) }
        }
    }
}

@Composable
fun DeleteProductionRow(checkboxEnabled: Boolean, production: ProductionEntity, onClickCheckBox: () -> Unit = {}) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Colors.WHITE, RoundedCornerShape(8.dp))
            .padding(28.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TriStateCheckbox(
            state = ToggleableState(checkboxEnabled),
            onClick = onClickCheckBox,
            modifier = Modifier.size(16.dp),
            colors = CheckboxDefaults.colors(
                Colors.BLUE,Colors.BLUE,
            )
        )
        Spacer(Modifier.width(18.dp))
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductionItem(
                text = production.country.name,
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.country.region.nameRu,
                color = Colors.GRAY_DARK
            )
        }
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.STEEL).formatDouble(),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.COAL).formatDouble(),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.OIL).formatDouble(),
                color = Colors.GRAY_DARK
            )
        }
    }
}

@Composable
fun EdiProductionRow(production: ProductionEntity, editMode: Boolean, onValueChange: (String, ManufactureType) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Colors.WHITE, RoundedCornerShape(8.dp))
            .padding(28.dp, 10.dp)
    ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EditProductionItem(
                value = production.country.name,
                readOnly = true
            )
            EditProductionItem(
                value = production.country.region.nameRu,
                readOnly = true
            )
        }
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EditProductionItem(
                value = production.manufacturers.findManufactureWithType(ManufactureType.STEEL).toString(),
                readOnly = !editMode
            ){
                onValueChange(it, ManufactureType.STEEL)
            }
            EditProductionItem(
                value = production.manufacturers.findManufactureWithType(ManufactureType.COAL).toString(),
                readOnly = !editMode
            ){
                onValueChange(it, ManufactureType.COAL)
            }
            EditProductionItem(
                value = production.manufacturers.findManufactureWithType(ManufactureType.OIL).toString(),
                readOnly = !editMode
            ){
                onValueChange(it, ManufactureType.OIL)
            }
        }
    }
}


@Composable
fun ProductionRow(production: ProductionEntity) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Colors.WHITE, RoundedCornerShape(8.dp))
            .padding(28.dp, 10.dp)
    ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductionItem(
                text = production.country.name,
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.country.region.nameRu,
                color = Colors.GRAY_DARK
            )
        }
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.STEEL).formatDouble(),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.COAL).formatDouble(),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.OIL).formatDouble(),
                color = Colors.GRAY_DARK
            )
        }
    }
}

private fun List<ManufactureEntity>.findManufactureWithType(type: ManufactureType): Double {
    return firstOrNull { it.type == type }?.value ?: 0.0
}

private fun Double.formatDouble() = "%.1f".format(this)

@Composable
fun TitleProductionRow(
    productionSortedType: ProductionSortedType,
    sortedType: SortedType,
    onClickItem: (ProductionSortedType) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xff4B90F7).copy(0.15f), RoundedCornerShape(8.dp))
            .padding(28.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier.weight(1f)
        ) {
            ProductionItem(
                text = "Страна",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COUNTRY},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.COUNTRY) }
            ProductionItem(
                text = "Регион",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.REGION},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.REGION) }
        }
        Row(
            Modifier.weight(1f)
        ) {
            ProductionItem(
                text = "Сталь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.STEEL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.STEEL) }
            ProductionItem(
                text = "Уголь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COAL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.COAL) }
            ProductionItem(
                text = "Нефть",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.OIL},
                pointerHover = PointerIcon.Hand
            ){ onClickItem(ProductionSortedType.OIL) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.ProductionItem(
    text: String,
    color: Color = Color(0xff949DA7),
    sortedType: SortedType? = null,
    pointerHover: PointerIcon = PointerIcon.Default,
    onClickItem: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .weight(1f),
        verticalAlignment = Alignment.CenterVertically
    ){
        AppText(
            modifier = Modifier
                .onClick{ onClickItem() }
                .pointerHoverIcon(pointerHover)
                .padding(vertical = 6.dp),
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (sortedType!=null){
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier.rotate(if (sortedType == SortedType.DESK) 180f else 0f),
                painter = painterResource(Res.drawable.ic_dropdown),
                contentDescription = null,
                tint = color
            )
        }
    }
}

@Composable
private fun RowScope.EditProductionItem(
    value: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        readOnly = readOnly,
        modifier = Modifier
            .weight(1f)
            .pointerHoverIcon(
                if (readOnly) PointerIcon.Default else PointerIcon.Text,
                true
            )
            .padding(vertical = 6.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Colors.GRAY_DARK,
        ),
    )
}