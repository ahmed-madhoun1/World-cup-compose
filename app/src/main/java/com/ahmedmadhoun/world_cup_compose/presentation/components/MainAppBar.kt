package com.ahmedmadhoun.world_cup_compose.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    showNotificationIcon:Boolean = false
) {

    val navController = rememberNavController()
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

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = appbarPadding),
        backgroundColor = whiteColor,
        elevation = 0.dp,
        title =
        {
            Text(
                text = screenTitle,
                color = primaryTextColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                ),
            )
        },
        navigationIcon = if (showBackButton) {
            {
                IconButton(
                    modifier = Modifier.size(iconSize),
                    onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        } else {
            null
        },
        actions = {
            if(showNotificationIcon){
                IconButton(
                    modifier = Modifier.size(iconSize),
                    onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.size(spacerSize))
            IconButton(
                modifier = Modifier.size(iconSize),
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.question_mark_icon),
                    contentDescription = null,
                )
            }
        }
    )

}