package com.ahmedmadhoun.world_cup_compose.util

fun getGroupName(group: Int) =
    when (group) {
        0 -> {
            "Group A"
        }
        1 -> {
            "Group B"
        }
        2 -> {
            "Group C"
        }
        3 -> {
            "Group D"
        }
        4 -> {
            "Group E"
        }
        5 -> {
            "Group F"
        }
        6 -> {
            "Group G"
        }
        7 -> {
            "Group H"
        }
        else -> {
            ""
        }
    }