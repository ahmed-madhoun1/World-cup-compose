package com.ahmedmadhoun.world_cup_compose.presentation.winner

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor
import com.google.gson.Gson

@Composable
fun WinnerScreen(
    viewModel: WinnerViewModel = hiltViewModel(),
    navController: NavController,
    winnerArgument: String?
) {

    val winner = remember {
        Gson().fromJson(winnerArgument, NationalTeam::class.java)
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "",
                showBackButton = true,
                navController = navController,
                titleDown = false,
                showProgressBar = false
            )
        }) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter).size(150.dp).padding(top = 20.dp),
                painter = painterResource(id = winner.image),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.win_design),
                contentDescription = null
            )
            Image(
                modifier = Modifier.align(Alignment.Center).padding(horizontal = 20.dp),
                painter = painterResource(id = R.drawable.win_banner),
                contentDescription = null
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = winner.name,
                color = primaryColor,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                ),
            )

            Spacer(Modifier.size(5.dp))
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 20.dp,
                        RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
                    )
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(color = whiteColor),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .align(Alignment.Center)
                ) {
                    PrimaryButton(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center),
                        text = R.string.submit_the_winner,
                        buttonColor = primaryColor,
                        isLoading = false,
                        isEnabled = true
                    ) {
                        // save data to room
                    }
                }
            }
        }
    }
}