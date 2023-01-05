package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.ahmedmadhoun.world_cup_compose.data.local.NationalTeam
import com.ahmedmadhoun.world_cup_compose.navigation.Screen
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupStageViewModel @Inject constructor(
//    private val repository: DriverRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(GroupStageState())
        internal set

    val list = mutableListOf(
            NationalTeam(id = 1, name = "QATAR", isQualified = false, group = 0, listType = 0),
            NationalTeam(id = 2, name = "ECUADOR", isQualified = false, group = 0, listType = 0),
            NationalTeam(id = 13, name = "SENEGAL", isQualified = false, group = 0, listType = 0),
            NationalTeam(
                id = 11,
                name = "NETHERLANDS",
                isQualified = false,
                group = 0,
                listType = 0
            ),
            NationalTeam(
                id = 132,
                name = "ENGLAND",
                isQualified = false,
                group = 1,
                listType = 0
            ),
            NationalTeam(id = 14, name = "IRAN", isQualified = false, group = 1, listType = 0),
            NationalTeam(id = 15, name = "USA", isQualified = false, group = 1, listType = 0),
            NationalTeam(id = 16, name = "WALES", isQualified = false, group = 1, listType = 0),
            NationalTeam(
                id = 17,
                name = "ARGENTINA",
                isQualified = false,
                group = 2,
                listType = 0
            ),
            NationalTeam(
                id = 18,
                name = "SAUDI ARABIA",
                isQualified = false,
                group = 2,
                listType = 0
            ),
            NationalTeam(id = 81, name = "MEXICO", isQualified = false, group = 2, listType = 0),
            NationalTeam(id = 19, name = "POLAND", isQualified = false, group = 2, listType = 0),
            NationalTeam(id = 61, name = "FRANCE", isQualified = false, group = 3, listType = 0),
            NationalTeam(
                id = 121,
                name = "AUSTRALIA",
                isQualified = false,
                group = 3,
                listType = 0
            ),
            NationalTeam(
                id = 1123,
                name = "DENMARK",
                isQualified = false,
                group = 3,
                listType = 0
            ),
            NationalTeam(
                id = 1435,
                name = "TUNISIA",
                isQualified = false,
                group = 3,
                listType = 0
            ),
            NationalTeam(
                id = 121412,
                name = "SPAIN",
                isQualified = false,
                group = 4,
                listType = 0
            ),
            NationalTeam(
                id = 1214124,
                name = "COSTA RICA",
                isQualified = false,
                group = 4,
                listType = 0
            ),
            NationalTeam(
                id = 3461,
                name = "GERMANY",
                isQualified = false,
                group = 4,
                listType = 0
            ),
            NationalTeam(
                id = 143568,
                name = "JAPAN",
                isQualified = false,
                group = 4,
                listType = 0
            ),
            NationalTeam(
                id = 5367351,
                name = "BELGIUM",
                isQualified = false,
                group = 5,
                listType = 0
            ),
            NationalTeam(
                id = 132453,
                name = "CANADA",
                isQualified = false,
                group = 5,
                listType = 0
            ),
            NationalTeam(
                id = 23581,
                name = "MOROCCO",
                isQualified = false,
                group = 5,
                listType = 0
            ),
            NationalTeam(
                id = 180869,
                name = "CROATIA",
                isQualified = false,
                group = 5,
                listType = 0
            ),
            NationalTeam(
                id = 1608,
                name = "BRAZIL",
                isQualified = false,
                group = 6,
                listType = 0
            ),
            NationalTeam(
                id = 1570,
                name = "SERBIA",
                isQualified = false,
                group = 6,
                listType = 0
            ),
            NationalTeam(
                id = 12004,
                name = "SWITZERLAND",
                isQualified = false,
                group = 6,
                listType = 0
            ),
            NationalTeam(
                id = 17563,
                name = "CAMEROON",
                isQualified = false,
                group = 6,
                listType = 0
            ),
            NationalTeam(id = 304, name = "GHANA", isQualified = false, group = 7, listType = 0),
            NationalTeam(
                id = 109871,
                name = "PORTUGAL",
                isQualified = false,
                group = 7,
                listType = 0
            ),
            NationalTeam(
                id = 12134076,
                name = "SOUTH KOREA",
                isQualified = false,
                group = 7,
                listType = 0
            ),
            NationalTeam(
                id = 983214,
                name = "URUGUAY",
                isQualified = false,
                group = 7,
                listType = 0
            ),
        )

    val groupStageList = list

//
//    private var _getLastUserBooking =
//        MutableStateFlow<List<HomeUsersBooking.BookingList.UserBooking>>(emptyList())
//    val getLastUserBooking: StateFlow<List<HomeUsersBooking.BookingList.UserBooking>> get() = _getLastUserBooking


    fun onEvent(event: GroupStageEvent) {
        when (event) {
            is GroupStageEvent.OnSubmit -> {
                val roundOf16List = mutableListOf<Match>()

                val firstInGroupList =
                   groupStageList.filter { it.isQualified && it.isFirstInGroup }
                        .sortedBy { it.group }

                for (i in firstInGroupList.indices step 2) {
                    roundOf16List.add(
                        Match(
                            team1 = firstInGroupList[i].copy(isQualified = false),
                            team2 = firstInGroupList[i + 1].copy(isQualified = false)
                        )
                    )
                }
                val secondInGroupList =
                    groupStageList.filter { it.isQualified && !it.isFirstInGroup }
                        .sortedBy { it.group }
                for (i in secondInGroupList.indices step 2) {
                    roundOf16List.add(
                        Match(
                            team1 = secondInGroupList[i].copy(isQualified = false),
                            team2 = secondInGroupList[i + 1].copy(isQualified = false)
                        )
                    )
                }
                event.navController.navigate(
                    Screen.RoundOf16Screen.withArgs(
                        Gson().toJson(
                            ListArgument(roundOf16List)
                        )
                    )
                )
            }
            is GroupStageEvent.OnCheckboxChanged -> {

            }
        }
    }


}
