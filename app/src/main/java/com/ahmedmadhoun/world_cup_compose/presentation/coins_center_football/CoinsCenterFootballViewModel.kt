package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmadhoun.world_cup_compose.domain.repository.NationalTeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsCenterFootballViewModel @Inject constructor(
    private val repository: NationalTeamsRepository,
) : ViewModel() {


    var state by mutableStateOf(CoinsCenterFootballState())
        internal set


    fun onEvent(event: CoinsCenterFootballEvent) {
        when (event) {
            CoinsCenterFootballEvent.OnSubmit -> {
                removeAllData()
            }
        }
    }

    private fun removeAllData() = viewModelScope.launch {
        repository.deleteAllDataJson()
        state = state.copy(screenSuccess = "All Data Deleted")
    }

}
