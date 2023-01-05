package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainProgressBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryButtonColor

@Composable
fun OfferCompose(navController: NavController)=
    Box() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (35).dp)
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.BottomCenter)
                .background(color = lightGreyColor),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)

            ) {
                Spacer(Modifier.size(20.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.coins_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .weight(3f)
                    ) {
                        MainProgressBar(7)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.esim_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .weight(1f)
                    )
                }
                Spacer(Modifier.size(20.dp))
                PrimaryButton(
                    text = R.string.get_free_usa_number,
                    buttonColor = primaryButtonColor,
                    isLoading = false,
                    isEnabled = true
                ) {
                    navController.navigate(Screen.GroupStageScreen.route)
                }
                Spacer(Modifier.size(30.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .wrapContentHeight()
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.coins_center_image),
                contentDescription = null
            )
        }
    }