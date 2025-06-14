package ru.donnu.practice.presentation.screens.home

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_add
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.AppButton
import ru.donnu.practice.presentation.ui.components.AppText
import kotlin.random.Random

@Composable
fun HomeScreen(onClickAdd: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        val viewModel = remember { HomeViewModel() }
        val state by viewModel.state.collectAsState()
        val listState = rememberLazyListState()
        Header(onClickAdd)
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            LazyColumn(
                modifier = Modifier.fillMaxHeight().weight(1f),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xff4B90F7).copy(0.15f), RoundedCornerShape(8.dp))
                            .padding(28.dp, 15.dp, 10.dp, 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row(
                            Modifier.weight(1f)
                        ){
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = "Страна",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xff949DA7)
                            )
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = "Регион",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xff949DA7)
                            )
                        }
                        Row(
                            Modifier.weight(1f)
                        ){
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = "Сталь",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xff949DA7)
                            )
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = "Уголь",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xff949DA7)
                            )
                            AppText(
                                modifier = Modifier.weight(1f),
                                text = "Нефть",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color(0xff949DA7)
                            )
                        }
                    }
                }
            }
            VerticalScrollbar(
                modifier = Modifier.fillMaxHeight().width(8.dp).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(scrollState = listState),
                style = LocalScrollbarStyle.current.copy(unhoverColor = Color(0xffEAEAEA), hoverColor = Colors.BLUE, shape = RoundedCornerShape(6.dp))
            )
        }
    }
}

@Composable
private fun Header(onClickAdd: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = "Таблица продукции",
            color = Colors.BLACK.copy(0.85f),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        AppButton(
            text = "Добавить",
            icon = Res.drawable.ic_add
        ) {
            onClickAdd()
        }
    }
}