package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor

@Composable
fun ChallengeCompose(modifier: Modifier, title: String, coinsText: String, image: Int) =
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = lightGreyColor)
            .padding(3.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(10.dp))
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Text(
            text = title,
            color = primaryTextColor,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            text = coinsText,
            color = primaryColor,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(Modifier.size(10.dp))
    }