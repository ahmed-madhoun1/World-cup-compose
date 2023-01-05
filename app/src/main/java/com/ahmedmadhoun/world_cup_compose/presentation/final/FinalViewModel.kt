package com.ahmedmadhoun.world_cup_compose.presentation.final

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FinalViewModel @Inject constructor(
//    private val repository: DriverRepository,
//    private val getDirectionInfo: GetDirectionInfo
) : ViewModel() {


    var state by mutableStateOf(FinalState())
        internal set

//
//    private var _getLastUserBooking =
//        MutableStateFlow<List<HomeUsersBooking.BookingList.UserBooking>>(emptyList())
//    val getLastUserBooking: StateFlow<List<HomeUsersBooking.BookingList.UserBooking>> get() = _getLastUserBooking


    fun onEvent(event: FinalEvent) {
        when (event) {
//            is HomeEvent.GetDriverWorkStatus -> {
//                getDriverStatus(event.token)
//            }
//
        }
    }

//
//    var nationalTeams: LiveData<List<NationalTeam>> = MutableLiveData(null)
//
//    private val _isRecyclerViewActive = MutableLiveData(true)
//    val isRecyclerViewActive: LiveData<Boolean> = _isRecyclerViewActive
//
//    fun setIsRecyclerViewActive(value: Boolean) {
//        _isRecyclerViewActive.value = value
//    }
//
//    fun getData(listType: Int) {
//        nationalTeams = repository.observeAllNationalTeams(listType = listType)
//    }
//
//
//    fun deleteAllNationalTeams() {
//        viewModelScope.launch(Dispatchers.Default) {
//            repository.deleteAllNationalTeams()
//        }
//    }
//
//    fun deleteNationalTeam(nationalTeam: NationalTeam) = viewModelScope.launch {
//        repository.deleteNationalTeam(nationalTeam)
//    }
//
//    fun insertNationalTeam(nationalTeam: NationalTeam) = viewModelScope.launch {
//        repository.insertNationalTeam(nationalTeam.copy(id = Random.nextInt(2000, 5000)))
//    }



}
