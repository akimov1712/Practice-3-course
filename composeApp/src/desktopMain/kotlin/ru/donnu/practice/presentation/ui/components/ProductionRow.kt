package ru.donnu.practice.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
                text = production.manufacturers.findManufactureWithType(ManufactureType.STEEL),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.COAL),
                color = Colors.GRAY_DARK
            )
            ProductionItem(
                text = production.manufacturers.findManufactureWithType(ManufactureType.OIL),
                color = Colors.GRAY_DARK
            )
        }
    }
}

private fun List<ManufactureEntity>.findManufactureWithType(type: ManufactureType):String{
    val double = firstOrNull { it.type == type }?.value ?: 0.0
    return "%.1f".format(double)
}

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
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COUNTRY}
            ){ onClickItem(ProductionSortedType.COUNTRY) }
            ProductionItem(
                text = "Регион",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.REGION}
            ){ onClickItem(ProductionSortedType.REGION) }
        }
        Row(
            Modifier.weight(1f)
        ) {
            ProductionItem(
                text = "Сталь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.STEEL}
            ){ onClickItem(ProductionSortedType.STEEL) }
            ProductionItem(
                text = "Уголь",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.COAL}
            ){ onClickItem(ProductionSortedType.COAL) }
            ProductionItem(
                text = "Нефть",
                sortedType = sortedType.takeIf { productionSortedType ==  ProductionSortedType.OIL}
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
                .pointerHoverIcon(PointerIcon.Hand)
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