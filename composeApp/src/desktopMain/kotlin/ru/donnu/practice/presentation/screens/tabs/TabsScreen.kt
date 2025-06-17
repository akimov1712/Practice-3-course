package ru.donnu.practice.presentation.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import practice_3_course.composeapp.generated.resources.Res
import practice_3_course.composeapp.generated.resources.ic_logo
import ru.donnu.practice.presentation.screens.add.AddScreen
import ru.donnu.practice.presentation.screens.home.HomeScreen
import ru.donnu.practice.presentation.screens.tabs.TabsEnum.*
import ru.donnu.practice.presentation.ui.Colors
import ru.donnu.practice.presentation.ui.components.AppText

@Composable
fun TabsScreen() {
    Row(
        modifier = Modifier.fillMaxSize()
    ){
        val viewModel = remember { TabsViewModel() }
        val state by viewModel.state.collectAsState()
        AsideNavigation(viewModel)
        Box(Modifier.fillMaxHeight().weight(1f).background(Colors.BG_GRAY)){
            when(state.tabs[state.selectedIndex]){
                Home -> HomeScreen{ viewModel.changeSelectedTabs(state.tabs.indexOf(TabsEnum.Add)) }
                Add -> AddScreen()
                Edit -> {}
                Delete -> {}
            }
        }
    }
}

@Composable
private fun AsideNavigation(viewModel: TabsViewModel){
    val density = LocalDensity.current
    var widthParent by remember { mutableStateOf(0.dp) }
    Column(
        modifier = Modifier.fillMaxHeight()
            .background(Colors.BG_BLUE)
            .padding(24.dp)
            .onGloballyPositioned{
                with(density){
                    widthParent = it.size.width.toDp()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Spacer(
            Modifier
                .padding(vertical = 24.dp)
                .width(widthParent)
                .height(1.dp)
                .background(Colors.WHITE.copy(0.15f))
        )
        TabsColumn(viewModel)
    }
}

@Composable
private fun TabsColumn(viewModel: TabsViewModel) {
    val density = LocalDensity.current
    var widthParent by remember { mutableStateOf(0.dp) }
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.onGloballyPositioned{
            with(density){
                widthParent = it.size.width.toDp()
            }
        },
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        state.tabs.forEachIndexed { index, tab ->
            TabsItem(
                text = tab.text,
                icon = tab.icon,
                enable = index == state.selectedIndex,
                modifier = Modifier.widthIn(min = widthParent)
            ){
                viewModel.changeSelectedTabs(index)
            }
        }
    }
}

@Composable
private fun TabsItem(text: String, icon: DrawableResource, enable: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    val bg = Colors.WHITE.takeIf { enable } ?: Color.Transparent
    val color = Colors.BLACK.takeIf { enable } ?: Colors.WHITE.copy(0.7f)
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .clickable(interactionSource, indication = ripple(), onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = color
        )
        Spacer(Modifier.width(14.dp))
        AppText(
            text = text,
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun Logo() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = null
        )
        Spacer(Modifier.width(12.dp))
        AppText(
            text = "Donnu app",
            color = Colors.WHITE,
            fontSize = 24.sp,
            fontWeight = ExtraBold
        )
    }
}