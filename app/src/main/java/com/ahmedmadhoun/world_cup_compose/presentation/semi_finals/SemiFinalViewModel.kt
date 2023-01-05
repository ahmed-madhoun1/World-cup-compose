package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SemiFinalViewModel @Inject constructor(
//    private val repository: DriverRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(SemiFinalState())
        internal set

//
//    private var _getLastUserBooking =
//        MutableStateFlow<List<HomeUsersBooking.BookingList.UserBooking>>(emptyList())
//    val getLastUserBooking: StateFlow<List<HomeUsersBooking.BookingList.UserBooking>> get() = _getLastUserBooking


    fun onEvent(event: SemiFinalEvent) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }


}
