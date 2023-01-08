package com.ahmedmadhoun.world_cup_compose.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals.QuarterFinalViewModel
import com.ahmedmadhoun.world_cup_compose.presentation.round_of_16.RoundOf16ViewModel
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor
import com.ahmedmadhoun.world_cup_compose.util.Rounds
import com.google.gson.Gson

@Composable
fun EleminationRounds(
    list: MutableList<Match>,
    navController: NavController,
    route: Screen,
    isReadFromLocal: Boolean = false,
    round: Rounds = Rounds.RoundOf16,
    viewModel: ViewModel
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current



    return if (isReadFromLocal) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(scrollState)
            ) {
                list.sortedBy { it.team1.group }.forEach { teams ->
                    Spacer(Modifier.size(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = lightGreyColor),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp)
                                    .weight(1.5f),
                                painter = painterResource(id = teams.team1.image),
                                contentDescription = null
                            )
                            Spacer(Modifier.size(10.dp))
                            Text(
                                modifier = Modifier.weight(8f),
                                text = teams.team1.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            MainCheckbox(
                                checked = teams.team1.isQualified,
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp)
                                    .weight(1.5f),
                                painter = painterResource(id = teams.team2.image),
                                contentDescription = null
                            )
                            Spacer(Modifier.size(10.dp))
                            Text(
                                modifier = Modifier.weight(8f),
                                text = teams.team2.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            MainCheckbox(
                                checked = teams.team2.isQualified,
                            )
                        }
                    }
                }
                Spacer(Modifier.size(150.dp))
            }
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 20.dp,
                        RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
                    )
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(color = whiteColor)
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
                        text = R.string.next,
                        buttonColor = primaryColor,
                        isLoading = false,
                        isEnabled = true
                    ) {
                        val nextRoundList = mutableListOf<Match>()

                        val sortedList =
                            list.filter { it.team1.isQualified || it.team2.isQualified }
                                .sortedBy { it.team1.group }

                        for (i in sortedList.indices step 2) {
                            val match1 = sortedList[i]
                            val match2 = sortedList[i + 1]
                            val team1: NationalTeam = if (match1.team1.isQualified) {
                                match1.team1
                            } else {
                                match1.team2
                            }
                            val team2: NationalTeam = if (match2.team1.isQualified) {
                                match2.team1
                            } else {
                                match2.team2
                            }
                            nextRoundList.add(
                                Match(
                                    team1 = team1.copy(isQualified = false),
                                    team2 = team2.copy(isQualified = false)
                                )
                            )
                        }

                        navController.navigate(route.withArgs(null))

                    }
                }
            }
        }
    } else {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(scrollState)
            ) {
                list.sortedBy { it.team1.group }.forEach { teams ->
                    Spacer(Modifier.size(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = lightGreyColor),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp)
                                    .weight(1.5f),
                                painter = painterResource(id = teams.team1.image),
                                contentDescription = null
                            )
                            Spacer(Modifier.size(10.dp))
                            Text(
                                modifier = Modifier.weight(8f),
                                text = teams.team1.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            val isChecked = remember { mutableStateOf(false) }
                            MainCheckbox(
                                checked = isChecked.value, onCheckedChange = { value ->
                                    isChecked.value = value
//                                    checkBoxClicked(isChecked.value, roundOf16List)
                                    if (teams.team2.isQualified) {
                                        Toast.makeText(
                                            context,
                                            "You can select just ONE team",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isChecked.value = false
                                    } else {
                                        teams.team1.isQualified = isChecked.value
                                    }
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(35.dp)
                                    .weight(1.5f),
                                painter = painterResource(id = teams.team2.image),
                                contentDescription = null
                            )
                            Spacer(Modifier.size(10.dp))
                            Text(
                                modifier = Modifier.weight(8f),
                                text = teams.team2.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            val isChecked = remember { mutableStateOf(false) }
                            MainCheckbox(
                                checked = isChecked.value, onCheckedChange = { value ->
                                    isChecked.value = value
//                                    checkBoxClicked(isChecked.value, roundOf16List)
                                    if (teams.team1.isQualified) {
                                        Toast.makeText(
                                            context,
                                            "You can select just ONE team",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isChecked.value = false
                                    } else {
                                        teams.team2.isQualified = isChecked.value
                                    }
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.size(150.dp))
            }
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 20.dp,
                        RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
                    )
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(color = whiteColor)
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
                        text = R.string.next,
                        buttonColor = primaryColor,
                        isLoading = false,
                        isEnabled = true
                    ) {
                        val sortedList =
                            list.filter { it.team1.isQualified || it.team2.isQualified }
                                .sortedBy { it.team1.group }
                        if (round == Rounds.RoundOf16 && sortedList.size != 8) {
                            Toast.makeText(
                                context,
                                "Please select one winner team from each group",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@PrimaryButton
                        }
                        if (round == Rounds.QuarterFinals && sortedList.size != 4) {
                            Toast.makeText(
                                context,
                                "Please select one winner team from each group",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@PrimaryButton
                        }

                        val nextRoundList = mutableListOf<Match>()
                        for (i in sortedList.indices step 2) {
                            val match1 = sortedList[i]
                            val match2 = sortedList[i + 1]
                            val team1: NationalTeam = if (match1.team1.isQualified) {
                                match1.team1
                            } else {
                                match1.team2
                            }
                            val team2: NationalTeam = if (match2.team1.isQualified) {
                                match2.team1
                            } else {
                                match2.team2
                            }
                            nextRoundList.add(
                                Match(
                                    team1 = team1.copy(isQualified = false),
                                    team2 = team2.copy(isQualified = false)
                                )
                            )
                        }
                        if (round == Rounds.RoundOf16) {
                            if (list.filter { it.team1.isQualified || it.team2.isQualified }.size == 8) {
                                (viewModel as RoundOf16ViewModel).insertRoundOf16List(
                                    DataJson(
                                        1,
                                        Gson().toJson(list)
                                    )
                                )
                            }
                        }
                        if (round == Rounds.QuarterFinals) {
                            (viewModel as QuarterFinalViewModel).insertQuarterFinalsList(
                                DataJson(
                                    2,
                                    Gson().toJson(list)
                                )
                            )
                        }
                        navController.navigate(
                            route.withArgs(
                                Gson().toJson(
                                    ListArgument(
                                        nextRoundList
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}