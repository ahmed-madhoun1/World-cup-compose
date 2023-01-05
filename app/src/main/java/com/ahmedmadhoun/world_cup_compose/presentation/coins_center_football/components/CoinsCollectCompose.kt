package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.goldButtonColor

@Composable
fun CoinsCollectCompose() = Column {
    Spacer(Modifier.size(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val modifier = Modifier.weight(1f)
        ChallengeCompose(
            modifier = modifier,
            title = "Challenge 01",
            coinsText = "+300 COINS",
            image = R.drawable.trophy
        )
        Spacer(Modifier.size(15.dp))
        ChallengeCompose(
            modifier = modifier,
            title = "Challenge 02",
            coinsText = "+100 COINS",
            image = R.drawable.trophy
        )
    }
    Spacer(Modifier.size(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val modifier = Modifier.weight(1f)
        ChallengeCompose(
            modifier = modifier,
            title = "New Game",
            coinsText = "+300 COINS",
            image = R.drawable.game
        )
        Spacer(Modifier.size(15.dp))
        ChallengeCompose(
            modifier = modifier,
            title = "Offers and Surveys",
            coinsText = "+40 COINS",
            image = R.drawable.checklist_icon
        )
    }
    Spacer(Modifier.size(20.dp))
    PrimaryButton(
        text = R.string.gold_button_text,
        buttonColor = goldButtonColor,
        isLoading = false,
        isEnabled = true
    ) {

    }
    Spacer(Modifier.size(30.dp))
}