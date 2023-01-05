package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmedmadhoun.world_cup_compose.R

@Composable
fun BannersCompose() =
    Row(modifier = Modifier.fillMaxWidth()) {
        BannerCompose(
            modifier = Modifier.weight(1f),
            text = "Predict and \n win!",
            image = R.drawable.banner_left
        )
        Spacer(Modifier.size(20.dp))
        BannerCompose(
            modifier = Modifier.weight(1f),
            text = "Football 2022 \n winner",
            image = R.drawable.banner_right
        )
    }