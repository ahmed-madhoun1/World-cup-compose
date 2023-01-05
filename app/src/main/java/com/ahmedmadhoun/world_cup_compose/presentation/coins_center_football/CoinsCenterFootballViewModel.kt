package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinsCenterFootballViewModel @Inject constructor(
//    private val repository: DriverRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(CoinsCenterFootballState())
        internal set

//
//    private var _getLastUserBooking =
//        MutableStateFlow<List<HomeUsersBooking.BookingList.UserBooking>>(emptyList())
//    val getLastUserBooking: StateFlow<List<HomeUsersBooking.BookingList.UserBooking>> get() = _getLastUserBooking


    fun onEvent(event: CoinsCenterFootballEvent) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }


}
