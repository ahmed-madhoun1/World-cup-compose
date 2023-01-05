package com.ahmedmadhoun.world_cup_compose.presentation.final

sealed class FinalEvent {
    data class GetDriverWorkStatus(val token: String) : FinalEvent()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        FinalEvent()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : FinalEvent()

}