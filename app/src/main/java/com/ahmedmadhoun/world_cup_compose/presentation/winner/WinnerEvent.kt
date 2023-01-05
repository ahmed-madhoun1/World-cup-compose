package com.ahmedmadhoun.world_cup_compose.presentation.winner

sealed class WinnerEvent {
    data class GetDriverWorkStatus(val token: String) : WinnerEvent()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        WinnerEvent()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : WinnerEvent()

}