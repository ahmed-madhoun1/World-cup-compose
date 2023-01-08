package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.CoinsCenterFootballEvent
import com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.CoinsCenterFootballViewModel
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor

@Composable
fun BannerCompose(
    text: String,
    image: Int,
    modifier: Modifier,
    viewModel: CoinsCenterFootballViewModel
) =
    Box(
        modifier = modifier.clickable {
            viewModel.onEvent(CoinsCenterFootballEvent.OnSubmit)
        },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null
        )
        Text(
            text = text,
            color = whiteColor,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h2.copy(
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start
            ),
        )
    }