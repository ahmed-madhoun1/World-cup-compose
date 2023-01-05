package com.ahmedmadhoun.world_cup_compose.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football.CoinsCenterFootballScreen
import com.ahmedmadhoun.world_cup_compose.presentation.final.FinalsScreen
import com.ahmedmadhoun.world_cup_compose.presentation.group_stage.GroupStageScreen
import com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals.QuarterFinalsScreen
import com.ahmedmadhoun.world_cup_compose.presentation.round_of_16.RoundOf16Screen
import com.ahmedmadhoun.world_cup_compose.presentation.semi_finals.SemiFinalsScreen
import com.ahmedmadhoun.world_cup_compose.presentation.winner.WinnerScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CoinsCenterFootballScreen.route
    ) {
        composable(route = Screen.CoinsCenterFootballScreen.route) {
            CoinsCenterFootballScreen(
                navController = navController
            )
        }

        composable(route = Screen.GroupStageScreen.route) {
            GroupStageScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.RoundOf16Screen.route + "/{round_of_16_list}",
            arguments = listOf(
                navArgument("round_of_16_list") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            RoundOf16Screen(
                navController = navController,
                roundOf16ListArgument = entry.arguments?.getString("round_of_16_list")
            )
        }

        composable(
            route = Screen.QuarterFinalsScreen.route + "/{quarter_finals_list}",
            arguments = listOf(
                navArgument("quarter_finals_list") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            QuarterFinalsScreen(
                navController = navController,
                quarterFinalsListArgument = entry.arguments?.getString("quarter_finals_list")
            )
        }

        composable(
            route = Screen.SemiFinalsScreen.route + "/{semi_finals_list}",
            arguments = listOf(
                navArgument("semi_finals_list") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            SemiFinalsScreen(
                navController = navController,
                semiFinalsListArgument = entry.arguments?.getString("semi_finals_list")
            )
        }

        composable(
            route = Screen.FinalsScreen.route + "/{finals_list}/{third_place_list}",
            arguments = listOf(
                navArgument("finals_list") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("third_place_list") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            FinalsScreen(
                navController = navController,
                finalsListArgument = entry.arguments?.getString("finals_list"),
                thirdPlaceListArgument = entry.arguments?.getString("third_place_list"),
            )
        }

        composable(
            route = Screen.WinnerScreen.route + "/{winner}",
            arguments = listOf(
                navArgument("winner") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            WinnerScreen(
                navController = navController,
                winner = entry.arguments?.getString("winner")
            )
        }

    }
}