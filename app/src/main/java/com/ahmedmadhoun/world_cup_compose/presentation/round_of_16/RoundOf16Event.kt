package com.ahmedmadhoun.world_cup_compose.presentation.round_of_16

sealed class RoundOf16Event {
    data class GetDriverWorkStatus(val token: String) : RoundOf16Event()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        RoundOf16Event()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : RoundOf16Event()

}