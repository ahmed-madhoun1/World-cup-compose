package com.ahmedmadhoun.world_cup_compose.navigation


sealed class Screen(val route: String) {
    object CoinsCenterFootballScreen : Screen(route = "coins_center_football_screen")
    object GroupStageScreen : Screen(route = "group_stage_screen")
    object RoundOf16Screen : Screen(route = "round_of_16_screen")
    object QuarterFinalsScreen : Screen(route = "quarter_finals_screen")
    object SemiFinalsScreen : Screen(route = "semi_finals_screen")
    object FinalsScreen : Screen(route = "finals_screen")
    object WinnerScreen : Screen(route = "winner_screen")

    fun withArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
