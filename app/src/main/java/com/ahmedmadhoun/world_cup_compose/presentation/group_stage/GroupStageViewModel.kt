package com.ahmedmadhoun.world_cup_compose.presentation.group_stage

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahmedmadhoun.world_cup_compose.R
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
        NationalTeam(
            id = 1,
            image = R.drawable.qatar,
            name = "QATAR",
            isQualified = false,
            group = 0,
            listType = 0
        ),
        NationalTeam(
            id = 2,
            image = R.drawable.ecuador,
            name = "ECUADOR",
            isQualified = false,
            group = 0,
            listType = 0
        ),
        NationalTeam(
            id = 13,
            image = R.drawable.senegal,
            name = "SENEGAL",
            isQualified = false,
            group = 0,
            listType = 0
        ),
        NationalTeam(
            id = 11,
            image = R.drawable.netherlands,
            name = "NETHERLANDS",
            isQualified = false,
            group = 0,
            listType = 0
        ),
        NationalTeam(
            id = 132,
            image = R.drawable.england,
            name = "ENGLAND",
            isQualified = false,
            group = 1,
            listType = 0
        ),
        NationalTeam(
            id = 14,
            image = R.drawable.iran,
            name = "IRAN",
            isQualified = false,
            group = 1,
            listType = 0
        ),
        NationalTeam(
            id = 15,
            image = R.drawable.usa,
            name = "USA",
            isQualified = false,
            group = 1,
            listType = 0
        ),
        NationalTeam(
            id = 16,
            image = R.drawable.wales,
            name = "WALES",
            isQualified = false,
            group = 1,
            listType = 0
        ),
        NationalTeam(
            id = 17,
            image = R.drawable.argentina,
            name = "ARGENTINA",
            isQualified = false,
            group = 2,
            listType = 0
        ),
        NationalTeam(
            id = 18,
            image = R.drawable.saudi_arabia,
            name = "SAUDI ARABIA",
            isQualified = false,
            group = 2,
            listType = 0
        ),
        NationalTeam(
            id = 81,
            image = R.drawable.mexico,
            name = "MEXICO",
            isQualified = false,
            group = 2,
            listType = 0
        ),
        NationalTeam(
            id = 19,
            image = R.drawable.poland,
            name = "POLAND",
            isQualified = false,
            group = 2,
            listType = 0
        ),
        NationalTeam(
            id = 61,
            image = R.drawable.france,
            name = "FRANCE",
            isQualified = false,
            group = 3,
            listType = 0
        ),
        NationalTeam(
            id = 121,
            image = R.drawable.australia,
            name = "AUSTRALIA",
            isQualified = false,
            group = 3,
            listType = 0
        ),
        NationalTeam(
            id = 1123,
            image = R.drawable.denmark,
            name = "DENMARK",
            isQualified = false,
            group = 3,
            listType = 0
        ),
        NationalTeam(
            id = 1435,
            image = R.drawable.tunisia,
            name = "TUNISIA",
            isQualified = false,
            group = 3,
            listType = 0
        ),
        NationalTeam(
            id = 121412,
            image = R.drawable.spain,
            name = "SPAIN",
            isQualified = false,
            group = 4,
            listType = 0
        ),
        NationalTeam(
            id = 1214124,
            image = R.drawable.costa_rica,
            name = "COSTA RICA",
            isQualified = false,
            group = 4,
            listType = 0
        ),
        NationalTeam(
            id = 3461,
            image = R.drawable.germany,
            name = "GERMANY",
            isQualified = false,
            group = 4,
            listType = 0
        ),
        NationalTeam(
            id = 143568,
            image = R.drawable.japan,
            name = "JAPAN",
            isQualified = false,
            group = 4,
            listType = 0
        ),
        NationalTeam(
            id = 5367351,
            image = R.drawable.belgium,
            name = "BELGIUM",
            isQualified = false,
            group = 5,
            listType = 0
        ),
        NationalTeam(
            id = 132453,
            image = R.drawable.canada,
            name = "CANADA",
            isQualified = false,
            group = 5,
            listType = 0
        ),
        NationalTeam(
            id = 23581,
            image = R.drawable.morocco,
            name = "MOROCCO",
            isQualified = false,
            group = 5,
            listType = 0
        ),
        NationalTeam(
            id = 180869,
            image = R.drawable.croatia,
            name = "CROATIA",
            isQualified = false,
            group = 5,
            listType = 0
        ),
        NationalTeam(
            id = 1608,
            image = R.drawable.brazil,
            name = "BRAZIL",
            isQualified = false,
            group = 6,
            listType = 0
        ),
        NationalTeam(
            id = 1570,
            image = R.drawable.serbia,
            name = "SERBIA",
            isQualified = false,
            group = 6,
            listType = 0
        ),
        NationalTeam(
            id = 12004,
            image = R.drawable.switzerland,
            name = "SWITZERLAND",
            isQualified = false,
            group = 6,
            listType = 0
        ),
        NationalTeam(
            id = 17563,
            image = R.drawable.cameron,
            name = "CAMEROON",
            isQualified = false,
            group = 6,
            listType = 0
        ),
        NationalTeam(
            id = 304,
            image = R.drawable.ghana,
            name = "GHANA",
            isQualified = false,
            group = 7,
            listType = 0
        ),
        NationalTeam(
            id = 109871,
            image = R.drawable.portugal,
            name = "PORTUGAL",
            isQualified = false,
            group = 7,
            listType = 0
        ),
        NationalTeam(
            id = 12134076,
            image = R.drawable.south_korea,
            name = "SOUTH KOREA",
            isQualified = false,
            group = 7,
            listType = 0
        ),
        NationalTeam(
            id = 983214,
            image = R.drawable.uruguay,
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
                if (groupStageList.filter { it.isQualified }.size == 16) {
                    val roundOf16List = mutableListOf<Match>()

                    val firstInGroupList =
                        groupStageList.filter { it.isQualified && it.isFirstInGroup }
                            .sortedBy { it.group }


                    val secondInGroupList =
                        groupStageList.filter { it.isQualified && !it.isFirstInGroup }
                            .sortedBy { it.group }

                    for (i in firstInGroupList.zip(secondInGroupList).indices  step 2){
                        roundOf16List.add(
                            Match(
                                team1 = firstInGroupList[i].copy(isQualified = false, group = 0),
                                team2 = secondInGroupList[i + 1].copy(isQualified = false, group = 0)
                            )
                        )
                        roundOf16List.add(
                            Match(
                                team1 = firstInGroupList[i + 1].copy(isQualified = false, group = 1),
                                team2 = secondInGroupList[i].copy(isQualified = false, group = 1)
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
                } else {
                    state = state.copy(screenError = "Please select TOW teams from each group")
                }
            }
            is GroupStageEvent.OnCheckboxChanged -> {

            }
        }
    }


}
