package ru.donnu.practice.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.Fonts

@Composable
fun AppTextField(
    value: String,
    placeholder: String,
    title: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
){
    Column {
        AppText(
            text = title,
            color = Colors.BLACK.copy(0.85f),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = modifier.clip(RoundedCornerShape(8.dp)).background(Colors.WHITE).padding(15.dp),
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
                modifier = Modifier.fillMaxSize(),
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
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
}
