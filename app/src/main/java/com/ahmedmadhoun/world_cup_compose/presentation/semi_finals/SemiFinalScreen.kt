package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.DataJson
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainCheckbox
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor
import com.google.gson.Gson

@Composable
fun SemiFinalsScreen(
    viewModel: SemiFinalViewModel = hiltViewModel(),
    navController: NavController,
    semiFinalsListArgument: String?
) {

    semiFinalsListArgument.let {
        if (semiFinalsListArgument != null && semiFinalsListArgument.isNotEmpty()) {
            val result: ListArgument? =
                Gson().fromJson(semiFinalsListArgument, ListArgument::class.java)
            if (result != null) {
                viewModel.semiFinalsList = result.list.toMutableList()
            }
        }
    }

    viewModel.observeData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.semiFinalsListLocal.isEmpty()) {
            viewModel.getSemiFinalsData(dataJson)
        }
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "SEMI FINALS",
                showBackButton = true,
                navController = navController,
                titleDown = true,
                progressValue = 10f
            )
        }) {

        if (viewModel.semiFinalsListLocal.isNotEmpty()) {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    viewModel.semiFinalsListLocal.sortedBy { it.team1.group }.forEach { teams ->
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
                                viewModel.semiFinalsListLocal.filter { it.team1.isQualified || it.team2.isQualified }
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

                            navController.navigate(Screen.FinalsScreen.withArgs(null, null))

                        }
                    }
                }
            }
        } else {
            Box() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    viewModel.semiFinalsList.forEach { teams ->
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
//                                    checkBoxClicked(isChecked.value, viewModel.semiFinalsList)
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
//                                    checkBoxClicked(isChecked.value, viewModel.semiFinalsList)
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
                            val finalList = mutableListOf<Match>()
                            val thirdPlaceList = mutableListOf<Match>()

                            val firstInGroupList =
                                viewModel.semiFinalsList.filter { it.team1.isQualified || it.team2.isQualified }

                            for (i in firstInGroupList.indices step 2) {
                                val team1: NationalTeam =
                                    if (viewModel.semiFinalsList[i].team1.isQualified) {
                                        viewModel.semiFinalsList[i].team1
                                    } else {
                                        viewModel.semiFinalsList[i].team2
                                    }
                                val team2: NationalTeam =
                                    if (viewModel.semiFinalsList[i + 1].team1.isQualified) {
                                        viewModel.semiFinalsList[i + 1].team1
                                    } else {
                                        viewModel.semiFinalsList[i + 1].team2
                                    }

                                val team3: NationalTeam =
                                    if (!viewModel.semiFinalsList[i].team1.isQualified) {
                                        viewModel.semiFinalsList[i].team1
                                    } else {
                                        viewModel.semiFinalsList[i].team2
                                    }
                                val team4: NationalTeam =
                                    if (!viewModel.semiFinalsList[i + 1].team1.isQualified) {
                                        viewModel.semiFinalsList[i + 1].team1
                                    } else {
                                        viewModel.semiFinalsList[i + 1].team2
                                    }

                                thirdPlaceList.add(
                                    Match(
                                        team1 = team3.copy(isQualified = false),
                                        team2 = team4.copy(isQualified = false)
                                    )
                                )
                                finalList.add(
                                    Match(
                                        team1 = team1.copy(isQualified = false),
                                        team2 = team2.copy(isQualified = false)
                                    )
                                )
                            }
                            if (firstInGroupList.size == 2) {
                                viewModel.insertSemiFinalsList(DataJson(3, Gson().toJson(viewModel.semiFinalsList)))
                                navController.navigate(
                                    Screen.FinalsScreen.withArgs(
                                        Gson().toJson(ListArgument(finalList)),
                                        Gson().toJson(ListArgument(thirdPlaceList))
                                    )
                                )
                            } else {
                                    Toast.makeText(context, "Please select one winner team from each group", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}