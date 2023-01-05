package com.ahmedmadhoun.world_cup_compose.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor

@Composable
fun PrimaryButton(
    text: Int,
    buttonColor: Color,
    isLoading: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = {
            onClick()
        },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor,
        ),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = whiteColor,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .align(Alignment.CenterVertically)
                    .border(border = BorderStroke(width = 0.2.dp, color = Color.Transparent))
            )
        } else {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.button,
                color = whiteColor
            )
        }
    }
}
