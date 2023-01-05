package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

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
import androidx.navigation.NavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.util.getGroupName
import com.ahmedmadhoun.world_cup_compose.util.rememberWindowInfo
import com.google.gson.Gson

@Composable
fun GroupStageScreen(
    viewModel: GroupStageViewModel = hiltViewModel(),
    navController: NavController,
) {

    val windowInfo = rememberWindowInfo()
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "GROUP STAGE",
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
                                    .padding(start = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.name,
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
                                        if (viewModel.groupStageList.filter { fil -> fil.group == item.group && fil.isQualified }.size >= 2 && isChecked.value) {
                                            Toast.makeText(context, "You can select just tow teams", Toast.LENGTH_SHORT).show()
                                            isChecked.value = false
                                        } else {
                                            viewModel.groupStageList.forEachIndexed { index, nationalTeam ->
                                                val nationalTeamItem = viewModel.groupStageList[index]
                                                if (nationalTeam.id == item.id) {
                                                    nationalTeamItem.isQualified = !nationalTeamItem.isQualified
                                                    if (!isChecked.value && !nationalTeamItem.isQualified && nationalTeamItem.isFirstInGroup) {
                                                        nationalTeamItem.isFirstInGroup = false
                                                        viewModel.groupStageList.forEach { team ->
                                                            if (team.group == nationalTeamItem.group && team.isQualified && !team.isFirstInGroup) {
                                                                team.isFirstInGroup = true
                                                                Toast.makeText(context, "${team.name} Became the first in group", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                    } else if (viewModel.groupStageList.any { team -> team.group == item.group && team.isQualified && team.isFirstInGroup }) {
                                                        Toast.makeText(context, "${nationalTeamItem.name} is the second in group", Toast.LENGTH_SHORT).show()
                                                    } else {
                                                        if (nationalTeamItem.isQualified) {
                                                            nationalTeamItem.isFirstInGroup = true
                                                            Toast.makeText(context, "${nationalTeamItem.name} is the First in group", Toast.LENGTH_SHORT).show()
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
            }
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
                    viewModel.onEvent(GroupStageEvent.OnSubmit(navController))
                }
            }
        }
    }
}

