package ru.donnu.practice.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import entity.DoubleRange
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.Fonts
import ru.donnu.practice.presentation.ui.components.AppButton
import ru.donnu.practice.presentation.ui.components.AppText
import ru.donnu.practice.utills.filterChars
import ru.donnu.practice.utills.isValidManufacture

@Composable
fun FilterDialog(
    coerceCoal: DoubleRange,
    coerceOil: DoubleRange,
    coerceSteel: DoubleRange,

    changeCoerceCoal: (DoubleRange) -> Unit,
    changeCoerceOil: (DoubleRange) -> Unit,
    changeCoerceSteel: (DoubleRange) -> Unit,

    onDismissRequest: () -> Unit,
){
    Dialog(
        onDismissRequest = onDismissRequest
    ){
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Colors.BG_GRAY)
                .padding(horizontal = 42.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val density = LocalDensity.current
            var widthParent by remember { mutableStateOf(0.dp) }
            AppText(
                text = "Фильтр",
                color = Colors.BLACK.copy(0.85f),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.onGloballyPositioned{
                    with(density){
                        widthParent = it.size.width.toDp()
                    }
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ){
                FilterTextField(
                    valueFrom = coerceSteel.from ?: "",
                    valueTo = coerceSteel.to ?: "",
                    title = "Сталь",
                    onChangeFrom = {
                        if (it.isValidManufacture()){
                            changeCoerceSteel(coerceSteel.copy(from = it.filterChars()))
                        }
                    },
                    onChangeTo = {
                        if (it.isValidManufacture()){
                            changeCoerceSteel(coerceSteel.copy(to = it.filterChars()))
                        }
                    }
                )
                FilterTextField(
                    valueFrom = coerceCoal.from ?: "",
                    valueTo = coerceCoal.to ?: "",
                    title = "Уголь",
                    onChangeFrom = {
                        if (it.isValidManufacture()){
                            changeCoerceCoal(coerceCoal.copy(from = it.filterChars()))
                        }
                    },
                    onChangeTo = {
                        if (it.isValidManufacture()){
                            changeCoerceCoal(coerceCoal.copy(to = it.filterChars()))
                        }
                    }
                )
                FilterTextField(
                    valueFrom = coerceOil.from ?: "",
                    valueTo = coerceOil.to ?: "",
                    title = "Нефть",
                    onChangeFrom = {
                        if (it.isValidManufacture()){
                            changeCoerceOil(coerceOil.copy(from = it.filterChars()))
                        }
                    },
                    onChangeTo = {
                        if (it.isValidManufacture()){
                            changeCoerceOil(coerceOil.copy(to = it.filterChars()))
                        }
                    }
                )
            }
            Column(
                 modifier = Modifier.width(widthParent),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Применить",
                    contentPadding = PaddingValues(vertical = 15.dp),
                ){
                    onDismissRequest()
                }
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Очистить",
                    backgroundColor = Colors.RED,
                    contentPadding = PaddingValues(vertical = 15.dp),
                ){
                    changeCoerceCoal(DoubleRange.EMPTY)
                    changeCoerceOil(DoubleRange.EMPTY)
                    changeCoerceSteel(DoubleRange.EMPTY)
                }
            }
        }
    }
}

@Composable
private fun FilterTextField(
    valueFrom: String,
    valueTo: String,
    title: String,
    onChangeFrom: (String) -> Unit,
    onChangeTo: (String) -> Unit,
){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        AppText(
            text = title,
            color = Colors.BLACK.copy(0.85f),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        TextField(
            value = valueFrom,
            placeholder = "От (млн. т.)",
            onValueChange = onChangeFrom,
        )
        TextField(
            value = valueTo,
            placeholder = "До (млн. т.)",
            onValueChange = onChangeTo,
        )
    }
}

@Composable
fun TextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
){
    Box(
        modifier = Modifier.width(160.dp).clip(RoundedCornerShape(8.dp)).background(Colors.WHITE).padding(15.dp),
        contentAlignment = Alignment.CenterStart,
    ){
        if (value.isEmpty()){
            AppText(
                text = placeholder,
                color = Colors.BLACK.copy(0.20f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Colors.BLACK.copy(0.85f),
                fontFamily = Fonts.montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        )
    }
}