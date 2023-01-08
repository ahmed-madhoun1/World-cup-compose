package com.ahmedmadhoun.world_cup_compose.presentation.round_of_16

import android.util.Log
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahmedmadhoun.world_cup_compose.R
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.ahmedmadhoun.world_cup_compose.presentation.components.EleminationRounds
import com.ahmedmadhoun.world_cup_compose.presentation.components.MainAppBar
import com.ahmedmadhoun.world_cup_compose.presentation.components.PrimaryButton
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.lightGreyColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryColor
import com.ahmedmadhoun.world_cup_compose.presentation.ui.theme.primaryTextColor
import com.google.gson.Gson

@Composable
fun RoundOf16Screen(
    viewModel: RoundOf16ViewModel = hiltViewModel(),
    navController: NavController,
    roundOf16ListArgument: String?,
) {


    roundOf16ListArgument.let {
        if (roundOf16ListArgument != null && roundOf16ListArgument.isNotEmpty()) {
            val result : ListArgument? = Gson().fromJson(roundOf16ListArgument, ListArgument::class.java)
            if(result != null){
                viewModel.roundOf16List = result.list.toMutableList()
            }
        }
    }


    val scrollState = rememberScrollState()
    val context = LocalContext.current

    viewModel.observeData.observeAsState().value.let { dataJson ->
        if (dataJson != null && viewModel.roundOf16ListLocal.isEmpty()) {
            viewModel.getRoundOf16Data(dataJson)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                screenTitle = "ROUND OF 16",
                showBackButton = true,
                navController = navController,
                titleDown = true,
                progressValue = 5f
            )
        }) {
        if(viewModel.roundOf16ListLocal.isNotEmpty()){
            EleminationRounds(
                viewModel.roundOf16ListLocal,
                navController,
                Screen.QuarterFinalsScreen,
                isReadFromLocal= true,
                viewModel = viewModel
            )
        }else{
            EleminationRounds(
                viewModel.roundOf16List,
                navController,
                Screen.QuarterFinalsScreen,
                isReadFromLocal= false,
                viewModel = viewModel
            )
        }
    }
}