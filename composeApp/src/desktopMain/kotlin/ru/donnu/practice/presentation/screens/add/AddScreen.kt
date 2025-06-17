package ru.donnu.practice.presentation.screens.add

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.AppButton
import ru.donnu.practice.presentation.ui.components.AppDropDown
import ru.donnu.practice.presentation.ui.components.AppText
import ru.donnu.practice.presentation.ui.components.AppTextField
import java.awt.SystemColor.text


@Composable
fun AddScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        val viewModel = remember { AddViewModel() }
        Title()
        Spacer(modifier = Modifier.height(50.dp))
        FieldsList(viewModel)
    }
}

@Composable
private fun FieldsList(viewModel: AddViewModel){
    val state by viewModel.state.collectAsState()
    val fieldsValid by viewModel.fieldsIsValid.collectAsState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        columns = GridCells.Fixed(3),
    ){
        item {
            AppDropDown(
                value = state.country?.name ?: "",
                items = listOf("Не выбрано") + state.countryList.map { it.name },
                placeholder = "США",
                title = "Страна",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = viewModel::changeCountry
            )
        }
        item {
            AppTextField(
                value = state.country?.region?.nameRu ?: "",
                enabled = false,
                placeholder = "Северная Америка",
                title = "Регион",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {  }
            )
        }
        item {
            AppTextField(
                value = state.steel,
                placeholder = "78.6",
                title = "Сталь",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = viewModel::changeSteel
            )
        }
        item {
            AppTextField(
                value = state.coal,
                placeholder = "660.6",
                title = "Уголь",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = viewModel::changeCoal
            )
        }
        item {
            AppTextField(
                value = state.oil,
                placeholder = "11.8",
                title = "Нефть",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = viewModel::changeOil
            )
        }
        item{
            AppButton(
                modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
                text = "Добавить продукцию",
                enabled = fieldsValid,
                contentPadding = PaddingValues(vertical = 15.dp),
            ){
                viewModel.addProduction()
            }
        }
    }
}

@Composable
private fun Title() {
    AppText(
        text = "Добавить продукцию",
        color = Colors.BLACK.copy(0.85f),
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold
    )
}