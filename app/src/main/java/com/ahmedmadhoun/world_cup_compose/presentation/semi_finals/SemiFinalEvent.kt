package com.ahmedmadhoun.world_cup_compose.presentation.semi_finals

sealed class SemiFinalEvent {
    data class GetDriverWorkStatus(val token: String) : SemiFinalEvent()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        SemiFinalEvent()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : SemiFinalEvent()

}