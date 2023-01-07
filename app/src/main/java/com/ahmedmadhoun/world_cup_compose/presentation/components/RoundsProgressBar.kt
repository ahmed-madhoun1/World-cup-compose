package com.ahmedmadhoun.world_cup_compose.presentation.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor

@Composable
fun RoundsProgressBar(progressCount: Float) {

    var ball2Size by remember { mutableStateOf(25) }
    var ball3Size by remember { mutableStateOf(25) }
    var ball4Size by remember { mutableStateOf(25) }
    var ball2 by remember { mutableStateOf(R.drawable.ball_not_colorfull) }
    var ball3 by remember { mutableStateOf(R.drawable.ball_not_colorfull) }
    var ball4 by remember { mutableStateOf(R.drawable.ball_not_colorfull) }

    var _progressCount: Float by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }

    /* to avoid the direct calculation of progress variable which is a Float
     and it can sometimes cause problems like it shows 0.4 to 0.400004 so, here I have use
     _progressCount and we will increase and decrease it and then convert it to progress(Float)
     and then use that progress with our ProgressBar Width*/
    when (_progressCount) {
        0f -> {
            progress = 0.0f
        }
        0.5f -> {
            progress = 0.05f
        }
        1f -> {
            progress = 0.1f
        }
        1.5f -> {
            progress = 0.15f
        }
        2f -> {
            progress = 0.2f
        }
        2.5f -> {
            progress = 0.25f
        }
        3f -> {
            progress = 0.3f
        }


        3.5f -> {
            progress = 0.35f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
        }
        4f -> {
            progress = 0.4f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
        }
        4.5f -> {
            progress = 0.45f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
        }
        5f -> {
            progress = 0.5f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
        }


        5.5f -> {
            progress = 0.55f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
        }
        6f -> {
            progress = 0.6f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
        }
        6.5f -> {
            progress = 0.65f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
        }
        7f -> {
            progress = 0.7f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
        }
        7.5f -> {
            progress = 0.75f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
        }
        8f -> {
            progress = 0.8f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
            ball4 = R.drawable.ball_colorfull
            ball4Size = 40
        }
        8.5f -> {
            progress = 0.85f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
            ball4 = R.drawable.ball_colorfull
            ball4Size = 40
        }
        9f -> {
            progress = 0.9f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
            ball4 = R.drawable.ball_colorfull
            ball4Size = 40
        }
        9.5f -> {
            progress = 0.95f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
            ball4 = R.drawable.ball_colorfull
            ball4Size = 40
        }
        10f -> {
            progress = 1.0f
            ball2 = R.drawable.ball_colorfull
            ball2Size = 40
            ball3 = R.drawable.ball_colorfull
            ball3Size = 40
            ball4 = R.drawable.ball_colorfull
            ball4Size = 40
        }
    }
    LaunchedEffect(key1 = true) {
        _progressCount = progressCount
    }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Progress Bar
        Box {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .height(8.dp)
            ) {
                // for the background of the ProgressBar
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(9.dp))
                        .background(lightGreyColor)
                )
                // for the progress of the ProgressBar
                Box(
                    modifier = Modifier
                        .fillMaxWidth(size)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(9.dp))
                        .background(primaryColor)
                        .animateContentSize()
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.ball_colorfull),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(ball2Size.dp),
                        painter = painterResource(id = ball2),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(ball3Size.dp),
                        painter = painterResource(id = ball3),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(ball4Size.dp),
                        painter = painterResource(id = ball4),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.esim_progress),
                        contentDescription = null
                    )
                }
            }
        }

    }


}











