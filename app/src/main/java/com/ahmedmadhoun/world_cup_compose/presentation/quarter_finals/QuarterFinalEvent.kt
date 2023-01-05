package com.ahmedmadhoun.world_cup_compose.presentation.quarter_finals

sealed class QuarterFinalEvent {
    data class GetDriverWorkStatus(val token: String) : QuarterFinalEvent()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        QuarterFinalEvent()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : QuarterFinalEvent()

}