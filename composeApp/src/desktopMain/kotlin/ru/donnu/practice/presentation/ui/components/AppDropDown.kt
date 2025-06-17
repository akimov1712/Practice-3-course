package ru.donnu.practice.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import entity.CountryEntity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_dropdown
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.Fonts
import java.awt.SystemColor.text

@Composable
fun AppDropDown(
    value: String,
    items: List<String>,
    placeholder: String,
    title: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
){

    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size(0F,0F)) }

    Column {
        AppText(
            text = title,
            color = Colors.BLACK.copy(0.85f),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Colors.WHITE)
                .onGloballyPositioned {
                    mTextFieldSize = it.size.toSize()
                }.clickable {
                    mExpanded = !mExpanded
                }
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AppText(
                text = placeholder.takeIf { value.isEmpty() } ?: value,
                color = Colors.BLACK.copy(if (value.isEmpty()) 0.20f else 0.85f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier.size(12.dp, 10.dp).rotate(if (mExpanded) 180f else 0f),
                painter = painterResource(Res.drawable.ic_dropdown),
                contentDescription = null,
                tint = Colors.BLACK.copy(0.20f)
            )
        }
        DropdownMenu(
            containerColor = Colors.WHITE,
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                .heightIn(max = 500.dp)
        ) {
            items.forEach {
                DropdownMenuItem(
                    text = {
                        AppText(
                            text = it,
                            color = Colors.BLACK.copy(0.45f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    onClick = {
                        onValueChange(it)
                        mExpanded = false
                    }
                )
            }
        }
    }
}
