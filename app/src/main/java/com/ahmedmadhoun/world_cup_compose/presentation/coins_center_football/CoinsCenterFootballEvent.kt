package com.ahmedmadhoun.world_cup_compose.presentation.coins_center_football

sealed class CoinsCenterFootballEvent {
    data class GetDriverWorkStatus(val token: String) : CoinsCenterFootballEvent()

    data class OnDriverWorkStatusChange(val token: String, val id: Int, val status: Boolean) :
        CoinsCenterFootballEvent()

//    data class DeleteItemFromList(
//        val list: List<HomeUsersBooking.BookingList.UserBooking>,
//        val index: Int
//    ) : CoinsCenterFootballEvent()

    object OnSubmit : CoinsCenterFootballEvent()

}