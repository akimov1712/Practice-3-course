package ru.donnu.practice.presentation.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import entity.ManufactureType
import entity.ProductionEntity
import entity.ProductionSortedType
import entity.SortedType
import org.jetbrains.compose.resources.painterResource
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_add
import practice_3_course.composeapp.generated.resources.ic_dropdown
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.AppButton
import ru.donnu.practice.presentation.ui.components.AppText
import ru.donnu.practice.presentation.ui.components.ProductionRow
import ru.donnu.practice.presentation.ui.components.TitleProductionRow
import ru.donnu.practice.utills.sortedWithType

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        val viewModel = remember { HomeViewModel() }
        val state by viewModel.state.collectAsState()
        Header{ viewModel.changeShowDialog(true) }
        Spacer(Modifier.height(24.dp))
        ProductionList(viewModel)
        if (state.showDialog){
            FilterDialog(
                coerceCoal = state.coerceCoal,
                coerceOil = state.coerceOil,
                coerceSteel = state.coerceSteel,
                changeCoerceOil = { viewModel.changeCoerce(ManufactureType.OIL, it) },
                changeCoerceCoal = { viewModel.changeCoerce(ManufactureType.COAL, it) },
                changeCoerceSteel = { viewModel.changeCoerce(ManufactureType.STEEL, it) },
                onDismissRequest = { viewModel.changeShowDialog(false) },
            )
        }
    }
}

@Composable
private fun ColumnScope.ProductionList(
    viewModel: HomeViewModel
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val sortedProduction by viewModel.sortedProduction.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth().weight(1f),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight().weight(1f),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                TitleProductionRow(
                    productionSortedType = state.productionSortedType,
                    sortedType = state.sortedType
                ) {
                    viewModel.changeSortedType(it)
                }
            }
            items(
                items = sortedProduction,
                key = { it.country.name }
            ) {
                ProductionRow(it)
            }
        }
        VerticalScrollbar(
            modifier = Modifier.fillMaxHeight().width(8.dp).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = listState),
            style = LocalScrollbarStyle.current.copy(
                unhoverColor = Color(0xffEAEAEA),
                hoverColor = Colors.BLUE,
                shape = RoundedCornerShape(6.dp)
            )
        )
    }
}

@Composable
private fun Header(onClickFilter: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "Таблица продукции (в млн. т.)",
            color = Colors.BLACK.copy(0.85f),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        AppButton(
            text = "Фильтры",
        ) {
            onClickFilter()
        }
    }
}