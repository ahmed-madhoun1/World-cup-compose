package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals.QuarterFinalViewModel
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.google.gson.Gson

@Composable
fun SemiFinalsScreen(
    viewModel: SemiFinalViewModel = hiltViewModel(),
    navController: NavController,
    semiFinalsListArgument: String?
) {

    val semiFinalList = remember {
        Gson().fromJson(semiFinalsListArgument, ListArgument::class.java).list
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "SEMI FINALS",
                showBackButton = true
            )
        }) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(scrollState)
            ) {
                semiFinalList.forEach { teams ->
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
                                .padding(start = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = teams.team1.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            val isChecked = remember { mutableStateOf(false) }
                            Checkbox(
                                enabled = true,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = primaryColor,
                                    uncheckedColor = primaryTextColor,
                                ),
                                checked = isChecked.value, onCheckedChange = { value ->
                                    isChecked.value = value
//                                    checkBoxClicked(isChecked.value, semiFinalList)
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
                                .padding(start = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = teams.team2.name,
                                color = primaryTextColor,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Start,
                                    fontSize = 18.sp
                                ),
                            )
                            val isChecked = remember { mutableStateOf(false) }
                            Checkbox(
                                enabled = true,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = primaryColor,
                                    uncheckedColor = primaryTextColor,
                                ),
                                checked = isChecked.value, onCheckedChange = { value ->
                                    isChecked.value = value
//                                    checkBoxClicked(isChecked.value, semiFinalList)
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
            }
            Spacer(Modifier.size(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.BottomCenter)
                    .background(color = lightGreyColor)
            ) {
                PrimaryButton(
                    modifier = Modifier.wrapContentSize(),
                    text = R.string.next,
                    buttonColor = primaryColor,
                    isLoading = false,
                    isEnabled = true
                ) {
                    val finalList = mutableListOf<Match>()
                    val thirdPlaceList = mutableListOf<Match>()

                    val firstInGroupList = semiFinalList.filter { it.team1.isQualified || it.team2.isQualified }

                    for (i in firstInGroupList.indices step 2) {
                        val team1: NationalTeam =
                            if (semiFinalList[i].team1.isQualified) {
                                semiFinalList[i].team1
                            } else {
                                semiFinalList[i].team2
                            }
                        val team2: NationalTeam =
                            if (semiFinalList[i + 1].team1.isQualified) {
                                semiFinalList[i + 1].team1
                            } else {
                                semiFinalList[i + 1].team2
                            }

                        val team3: NationalTeam =
                            if (!semiFinalList[i].team1.isQualified) {
                                semiFinalList[i].team1
                            } else {
                                semiFinalList[i].team2
                            }
                        val team4: NationalTeam =
                            if (!semiFinalList[i + 1].team1.isQualified) {
                                semiFinalList[i + 1].team1
                            } else {
                                semiFinalList[i + 1].team2
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

                    Log.e("ASD", "SemiFinalsScreen: ${finalList}", )
                    Log.e("ASD", "SemiFinalsScreen: ${thirdPlaceList}", )
                    navController.navigate(
                        Screen.FinalsScreen.withArgs(
                            Gson().toJson(ListArgument(finalList)),
                            Gson().toJson(ListArgument(thirdPlaceList))
                        )
                    )
                }
            }
        }
    }

}