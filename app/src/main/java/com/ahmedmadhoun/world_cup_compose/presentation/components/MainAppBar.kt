package com.ahmedmadhoun.world_cup_compose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor
import com.ahmedmadhoun.world_cup_compose.util.WindowInfo
import com.ahmedmadhoun.world_cup_compose.util.rememberWindowInfo

@Composable
fun MainAppBar(
    screenTitle: String,
    showBackButton: Boolean = rememberNavController().previousBackStackEntry != null,
    showNotificationIcon: Boolean = false,
    titleDown: Boolean = false,
    progressValue: Float = 2f,
    navController: NavController,
    showProgressBar: Boolean = true
) {

    val windowInfo = rememberWindowInfo()

    val iconSize: Dp
    val spacerSize: Dp
    val appbarPadding: Dp

    when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Compact -> {
            iconSize = 25.dp
            spacerSize = 10.dp
            appbarPadding = 18.dp
        }
        is WindowInfo.WindowType.Medium -> {
            iconSize = 45.dp
            spacerSize = 15.dp
            appbarPadding = 24.dp
        }
        else -> {
            iconSize = 70.dp
            spacerSize = 30.dp
            appbarPadding = 30.dp
        }
    }


    val appBarSize = if (!titleDown) {
        100.dp
    } else {
        150.dp
    }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(appBarSize)
            .padding(horizontal = appbarPadding, vertical = 20.dp),
        backgroundColor = whiteColor,
        elevation = 0.dp,
    ) {
        if (titleDown) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (showBackButton) {
                        IconButton(
                            modifier = Modifier
                                .size(iconSize)
                                .weight(1f),
                            onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(12f))
                    if (showNotificationIcon) {
                        IconButton(
                            modifier = Modifier
                                .size(iconSize)
                                .weight(1f),
                            onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.notification_icon),
                                contentDescription = null,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(spacerSize))
                    IconButton(
                        modifier = Modifier
                            .size(iconSize)
                            .weight(1f),
                        onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.question_mark_icon),
                            contentDescription = null,
                        )
                    }
                }
                Spacer(Modifier.size(5.dp))
                Text(
                    modifier = Modifier,
                    text = screenTitle,
                    color = primaryTextColor,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Start
                    ),
                )
                Spacer(Modifier.size(10.dp))
                if (showProgressBar) {
                    RoundsProgressBar(progressValue)
                }
            }
        } else {
            Row(modifier = Modifier.fillMaxWidth()) {
                if (showBackButton) {
                    IconButton(
                        modifier = Modifier
                            .size(iconSize)
                            .weight(1f),
                        onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
                Text(
                    modifier = Modifier.weight(12f),
                    text = screenTitle,
                    color = primaryTextColor,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2.copy(
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    ),
                )
                if (showNotificationIcon) {
                    IconButton(
                        modifier = Modifier
                            .size(iconSize)
                            .weight(1f),
                        onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_icon),
                            contentDescription = null,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(spacerSize))
                IconButton(
                    modifier = Modifier
                        .size(iconSize)
                        .weight(1f),
                    onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.question_mark_icon),
                        contentDescription = null,
                    )
                }
            }
        }
    }

}