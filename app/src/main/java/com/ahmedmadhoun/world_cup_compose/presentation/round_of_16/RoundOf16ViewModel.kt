package com.ahmedmadhoun.world_cup_compose.presentation.round_of_16

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahmedmadhoun.world_cup_compose.data.local.ListArgument
import com.ahmedmadhoun.world_cup_compose.data.local.Match
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoundOf16ViewModel @Inject constructor(
//    private val repository: DriverRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(RoundOf16State())
        internal set


    var roundOf16List = mutableListOf<Match>()


//
//    private var _getLastUserBooking =
//        MutableStateFlow<List<HomeUsersBooking.BookingList.UserBooking>>(emptyList())
//    val getLastUserBooking: StateFlow<List<HomeUsersBooking.BookingList.UserBooking>> get() = _getLastUserBooking


    fun onEvent(event: RoundOf16Event) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }


}
