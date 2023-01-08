package com.ahmedmadhoun.world_cup_compose.presentation.final_screen

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

// Never ever name a file "final" :) ************************

@Composable
fun FinalsScreen(
    viewModel: FinalViewModel = hiltViewModel(),
    navController: NavController,
    finalsListArgument: String?,
    thirdPlaceListArgument: String?,
) {
    finalsListArgument.let {
        if (finalsListArgument != null && finalsListArgument.isNotEmpty()) {
            val result: ListArgument? =
                Gson().fromJson(finalsListArgument, ListArgument::class.java)
            if (result != null) {
                viewModel.finalsList = result.list.toMutableList()
            }
        }
    }

    thirdPlaceListArgument.let {
        if (thirdPlaceListArgument != null && thirdPlaceListArgument.isNotEmpty()) {
            val result: ListArgument? =
                Gson().fromJson(thirdPlaceListArgument, ListArgument::class.java)
            if (result != null) {
                viewModel.thirdPlaceList = result.list.toMutableList()
            }
        }
    }

    viewModel.observeData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.finalsListLocal.isEmpty()) {
            viewModel.getFinalsData(dataJson)
        }
    }

    viewModel.observeThirdPlaceData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.thirdPlaceListLocal.isEmpty()) {
            viewModel.getThirdPlaceData(dataJson)
        }
    }


    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "FINALS",
                showBackButton = true,
                navController = navController,
                titleDown = true,
                progressValue = 10f
            )
        }) {
        if (viewModel.finalsListLocal.isNotEmpty()) {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    viewModel.finalsListLocal.forEach { teams ->
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
                                    checked = teams.team1.isQualified
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
                                    checked = teams.team2.isQualified
                                )
                            }
                        }
                    }
                    Spacer(Modifier.size(20.dp))
                    Text(
                        text = "Third Place",
                        color = primaryTextColor,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Spacer(Modifier.size(10.dp))
                    viewModel.thirdPlaceListLocal.forEach { teams ->
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
                                    checked = teams.team1.isQualified
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
                                    checked = teams.team2.isQualified
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
                            navController.navigate(Screen.WinnerScreen.withArgs(Gson().toJson(viewModel.winner)))
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
                    viewModel.finalsList.forEach { teams ->
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
//                                    checkBoxClicked(isChecked.value, viewModel.finalsList)
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
//                                    checkBoxClicked(isChecked.value, viewModel.finalsList)
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
                    Spacer(Modifier.size(20.dp))
                    Text(
                        text = "Third Place",
                        color = primaryTextColor,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center
                        ),
                    )
                    Spacer(Modifier.size(10.dp))
                    viewModel.thirdPlaceList.forEach { teams ->
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
//                                    checkBoxClicked(isChecked.value, viewModel.finalsList)
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
//                                    checkBoxClicked(isChecked.value, viewModel.finalsList)
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

                            val firstInGroupList =
                                viewModel.finalsList.filter { it.team1.isQualified || it.team2.isQualified }

                            viewModel.insertFinalsList(
                                DataJson(
                                    4,
                                    Gson().toJson(viewModel.finalsList)
                                )
                            )
                            viewModel.insertFinalsList(
                                DataJson(
                                    5,
                                    Gson().toJson(viewModel.thirdPlaceList)
                                )
                            )


                            val winner: NationalTeam =
                                if (viewModel.finalsList[0].team1.isQualified) {
                                    viewModel.finalsList[0].team1
                                } else {
                                    viewModel.finalsList[0].team2
                                }

                            navController.navigate(
                                Screen.WinnerScreen.withArgs(
                                    Gson().toJson(winner)
                                )
                            )

                        }
                    }
                }
            }
        }
    }


}
