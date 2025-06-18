package ru.donnu.practice.presentation.screens.edit

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_edit
import practice_3_course.composeapp.generated.resources.ic_save
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.AppButton
import ru.donnu.practice.presentation.ui.components.AppText
import ru.donnu.practice.presentation.ui.components.EdiProductionRow
import ru.donnu.practice.presentation.ui.components.ProductionRow
import ru.donnu.practice.presentation.ui.components.TitleProductionRow

@Composable
fun EditScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        val viewModel = remember { EditViewModel() }
        val state by viewModel.state.collectAsState()
        Header(state.editMode, viewModel::changeEditMode)
        Spacer(Modifier.height(24.dp))
        ProductionList(viewModel)
    }
}

@Composable
private fun ColumnScope.ProductionList(
    viewModel: EditViewModel
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
                EdiProductionRow(
                    production = it,
                    editMode = state.editMode
                ){ value, type ->
                    viewModel.changeValueField(it.country, type, value)
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
private fun Header(modeEdit: Boolean, onClickEdit: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "Редактировать таблицу",
            color = Colors.BLACK.copy(0.85f),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        AppButton(
            text = if (modeEdit) "Сохранить" else "Изменить",
            icon = if (modeEdit) Res.drawable.ic_save else Res.drawable.ic_edit
        ) {
            onClickEdit()
        }
    }
}