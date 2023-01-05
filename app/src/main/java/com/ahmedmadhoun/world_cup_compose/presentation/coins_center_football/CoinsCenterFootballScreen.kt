package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components.*
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.util.WindowInfo
import com.ahmedmadhoun.world_cup_compose.util.rememberWindowInfo

@Composable
fun CoinsCenterFootballScreen(
    viewModel: CoinsCenterFootballViewModel = hiltViewModel(),
    navController: NavController,
) {

    val windowInfo = rememberWindowInfo()
    val scrollState = rememberScrollState()

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MainAppBar(
                    screenTitle = "Coins Center",
                    showBackButton = true,
                    showNotificationIcon = true
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp)
                    .verticalScroll(scrollState)
            ) {
                BannersCompose()
                Spacer(Modifier.size(20.dp))
                OfferCompose(navController)
                Spacer(Modifier.size(60.dp))
                AdsCompose()
                CoinsCollectCompose()
            }
        }
    } else if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Medium) {

    } else {

    }


}