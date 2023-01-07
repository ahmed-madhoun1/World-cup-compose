package com.ahmedmadhoun.world_cup_compose.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.fieldBorderColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.greyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor

@Composable
fun MainCheckbox(
    modifier: Modifier = Modifier,
    size: Float = 24f,
    checkedColor: Color = primaryColor,
    uncheckedColor: Color = White,
    checkmarkColor: Color = White,
    onCheckedChange: ((Boolean) -> Unit),
    checked: Boolean,
) {

    val checkboxColor: Color by animateColorAsState(if (checked) checkedColor else uncheckedColor)
    val density = LocalDensity.current
    val duration = 200


    Column(
        modifier = modifier
            .border(2.dp, fieldBorderColor, RoundedCornerShape(8.dp))
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = { onCheckedChange(!checked) }
            )
            .size(size.dp)
            .background(checkboxColor, RoundedCornerShape(8.dp)),
    ) {
        androidx.compose.animation.AnimatedVisibility(
            visible = checked,
            enter = slideInHorizontally(
                animationSpec = tween(duration)
            ) {
                with(density) { (size * -0.5).dp.roundToPx() }
            } + expandHorizontally(
                expandFrom = Alignment.Start,
                animationSpec = tween(duration)
            ),
            exit = fadeOut()
        ) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = checkmarkColor
            )
        }
    }
}