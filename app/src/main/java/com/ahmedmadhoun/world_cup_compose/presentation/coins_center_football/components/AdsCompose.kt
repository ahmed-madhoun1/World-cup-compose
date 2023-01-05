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
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor

@Composable
fun AdsCompose()=
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(20.dp))
            .background(color = lightGreyColor),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.info_icon),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ads_icon),
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1.2f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ads_banner_tag),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 5.dp),
                    text = "REWARDED AD",
                    color = whiteColor,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    ),
                )
            }
        }
        Spacer(Modifier.size(30.dp))
        Text(
            text = "Watch videos over the day",
            color = primaryTextColor,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )
        Spacer(Modifier.size(15.dp))
        Text(
            text = "3-5 COINS",
            color = primaryColor,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(Modifier.size(30.dp))
    }