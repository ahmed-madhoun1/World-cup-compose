package com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.EleminationRounds
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.ahmedmadhoun.world_cup_compose.util.Rounds
import com.google.gson.Gson

@Composable
fun QuarterFinalsScreen(
    viewModel: QuarterFinalViewModel = hiltViewModel(),
    navController: NavController,
    quarterFinalsListArgument: String?
) {

    quarterFinalsListArgument.let {
        if (quarterFinalsListArgument != null && quarterFinalsListArgument.isNotEmpty()) {
            val result : ListArgument? = Gson().fromJson(quarterFinalsListArgument, ListArgument::class.java)
            if(result != null){
                viewModel.quarterFinalsList = result.list.toMutableList()
            }
        }
    }

    viewModel.observeData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.quarterFinalsListLocal.isEmpty()) {
            viewModel.getQuarterFinalsData(dataJson)
        }
    }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "QUARTER FINALS",
                showBackButton = true,
                navController=navController,
                titleDown = true,
                progressValue = 7.5f
            )
        }) {
        if(viewModel.quarterFinalsListLocal.isNotEmpty()){
            EleminationRounds(
                viewModel.quarterFinalsListLocal,
                navController,
                Screen.SemiFinalsScreen,
                isReadFromLocal= true,
                round= Rounds.QuarterFinals,
                viewModel = viewModel
            )
        }else{
            EleminationRounds(
                viewModel.quarterFinalsList,
                navController,
                Screen.SemiFinalsScreen,
                isReadFromLocal= false,
                round= Rounds.QuarterFinals,
                viewModel = viewModel
            )
        }
    }
}