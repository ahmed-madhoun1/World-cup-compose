package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainCheckbox
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.whiteColor
import com.ahmedmadhoun.world_cup_compose.util.getGroupName
import com.ahmedmadhoun.world_cup_compose.util.rememberWindowInfo

@Composable
fun GroupStageScreen(
    viewModel: GroupStageViewModel = hiltViewModel(),
    navController: NavController,
) {

    val windowInfo = rememberWindowInfo()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val state = viewModel.state
    if (state.screenError.isNotEmpty()) {
        Toast.makeText(context, state.screenError, Toast.LENGTH_SHORT).show()
    }



    viewModel.observeData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.groupStageListLocal.isEmpty()) {
            viewModel.getGroupStageData(dataJson)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "GROUP STAGE",
                showBackButton = true,
                navController = navController,
                titleDown = true,
                progressValue = 3f
            )
        }) {
        if (viewModel.groupStageListLocal.isNotEmpty()) {
            Box {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 20.dp, end = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    viewModel.groupStageListLocal.groupBy {
                        it.group
                    }.values.map {
                        Spacer(Modifier.size(20.dp))
                        Text(
                            modifier = Modifier,
                            text = getGroupName(it[0].group),
                            color = primaryTextColor,
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                                fontSize = 20.sp
                            ),
                        )
                        Spacer(Modifier.size(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = lightGreyColor),
                        ) {
                            it.forEach { item ->
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
                                        painter = painterResource(id = item.image),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.size(10.dp))
                                    Text(
                                        modifier = Modifier.weight(8f),
                                        text = item.name,
                                        color = primaryTextColor,
                                        style = MaterialTheme.typography.subtitle1.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Start,
                                            fontSize = 18.sp
                                        ),
                                    )
                                    if (item.isFirstInGroup) {
                                        Text(
                                            modifier = Modifier,
                                            text = "1",
                                            color = primaryTextColor,
                                            style = MaterialTheme.typography.subtitle1.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                textAlign = TextAlign.Start,
                                                fontSize = 18.sp
                                            ),
                                        )
                                    }
                                    if (!item.isFirstInGroup && item.isQualified) {
                                        Text(
                                            modifier = Modifier,
                                            text = "2",
                                            color = primaryTextColor,
                                            style = MaterialTheme.typography.subtitle1.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                textAlign = TextAlign.Start,
                                                fontSize = 18.sp
                                            ),
                                        )
                                    }
                                    MainCheckbox(checked = item.isQualified)
                                }
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
                            navController.navigate(Screen.RoundOf16Screen.withArgs(null))
                        }
                    }
                }
            }
        } else {
            Box {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 20.dp, end = 20.dp)
                        .verticalScroll(scrollState)
                ) {
                    viewModel.list.groupBy {
                        it.group
                    }.values.map {
                        Spacer(Modifier.size(20.dp))
                        Text(
                            modifier = Modifier,
                            text = getGroupName(it[0].group),
                            color = primaryTextColor,
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start,
                                fontSize = 20.sp
                            ),
                        )
                        Spacer(Modifier.size(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = lightGreyColor),
                        ) {
                            it.forEach { item ->
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
                                        painter = painterResource(id = item.image),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.size(10.dp))
                                    Text(
                                        modifier = Modifier.weight(8f),
                                        text = item.name,
                                        color = primaryTextColor,
                                        style = MaterialTheme.typography.subtitle1.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Start,
                                            fontSize = 18.sp
                                        ),
                                    )
                                    if (item.isFirstInGroup) {
                                        Text(
                                            modifier = Modifier,
                                            text = "1",
                                            color = primaryTextColor,
                                            style = MaterialTheme.typography.subtitle1.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                textAlign = TextAlign.Start,
                                                fontSize = 18.sp
                                            ),
                                        )
                                    }
                                    if (!item.isFirstInGroup && item.isQualified) {
                                        Text(
                                            modifier = Modifier,
                                            text = "2",
                                            color = primaryTextColor,
                                            style = MaterialTheme.typography.subtitle1.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                textAlign = TextAlign.Start,
                                                fontSize = 18.sp
                                            ),
                                        )
                                    }
                                    val isChecked = remember { mutableStateOf(false) }
                                    MainCheckbox(
                                        checked = isChecked.value,
                                        onCheckedChange = { value ->
                                            isChecked.value = value
                                            if (viewModel.groupStageList.filter { fil -> fil.group == item.group && fil.isQualified }.size >= 2 && isChecked.value) {
                                                Toast.makeText(
                                                    context,
                                                    "You can select just tow teams",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                isChecked.value = false
                                            } else {
                                                viewModel.groupStageList.forEachIndexed { index, nationalTeam ->
                                                    val nationalTeamItem =
                                                        viewModel.groupStageList[index]
                                                    if (nationalTeam.id == item.id) {
                                                        nationalTeamItem.isQualified =
                                                            !nationalTeamItem.isQualified
                                                        if (!isChecked.value && !nationalTeamItem.isQualified && nationalTeamItem.isFirstInGroup) {
                                                            nationalTeamItem.isFirstInGroup =
                                                                false
                                                            viewModel.groupStageList.forEach { team ->
                                                                if (team.group == nationalTeamItem.group && team.isQualified && !team.isFirstInGroup) {
                                                                    team.isFirstInGroup =
                                                                        true
                                                                    Toast.makeText(
                                                                        context,
                                                                        "${team.name} Became the first in group",
                                                                        Toast.LENGTH_SHORT
                                                                    ).show()
                                                                }
                                                            }
                                                        } else {
                                                            if (!viewModel.groupStageList.any { team -> team.group == item.group && team.isQualified && team.isFirstInGroup }) {
                                                                nationalTeamItem.isFirstInGroup =
                                                                    true
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    )
                                }
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
                            viewModel.onEvent(GroupStageEvent.OnSubmit(navController))
                        }
                    }
                }
            }
        }
    }

}

