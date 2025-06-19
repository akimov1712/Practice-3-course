package ru.donnu.practice.presentation.screens.delete

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_add
import practice_3_course.composeapp.generated.resources.ic_delete
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.*

@Composable
fun DeleteScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        val viewModel = remember { DeleteViewModel() }
        Header{ viewModel.deleteProduction() }
        Spacer(Modifier.height(24.dp))
        ProductionList(viewModel)
    }
}

@Composable
private fun ColumnScope.ProductionList(
    viewModel: DeleteViewModel
) {
    val state by viewModel.state.collectAsState()
    val generalCondition by viewModel.generalCondition.collectAsState()
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
                TitleDeleteProductionRow(
                    productionSortedType = state.productionSortedType,
                    sortedType = state.sortedType,
                    state = generalCondition,
                    onClickCheckBox = { viewModel.toggleGeneralCondition() }
                ) {
                    viewModel.changeSortedType(it)
                }
            }
            items(
                items = sortedProduction,
                key = { it.country.name }
            ) {
                DeleteProductionRow(
                    checkboxEnabled = state.deleteIds.contains(it.country.id),
                    production = it
                ){
                    viewModel.toggleDeleteId(it.country.id)
                }
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
private fun Header(onClickDelete: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "Удалить продукцию",
            color = Colors.BLACK.copy(0.85f),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        AppButton(
            text = "Удалить",
            icon = Res.drawable.ic_delete,
            backgroundColor = Colors.RED
        ) {
            onClickDelete()
        }
    }
}