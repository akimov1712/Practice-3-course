package ru.donnu.practice.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import practice_3_course.composeapp.generated.resources.Res
import ru.donnu.practice.presentation.ui.Colors

@Composable
fun AppButton(
    text: String,
    icon: DrawableResource? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = RoundedCornerShape(8.dp),
    border: BorderStroke? = null,
    backgroundColor: Color = Colors.BLUE,
    contentColor: Color = Colors.WHITE,
    contentPadding: PaddingValues = PaddingValues(18.dp, 12.dp, 30.dp, 12.dp),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = ButtonDefaults.buttonColors(backgroundColor, contentColor),
        contentPadding = contentPadding,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            if (icon != null) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = contentColor
                )
                Spacer(Modifier.width(14.dp))
            }
            AppText(
                text = text,
                color = contentColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}