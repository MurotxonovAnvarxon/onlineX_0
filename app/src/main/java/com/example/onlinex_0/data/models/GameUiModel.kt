package com.example.onlinex_0.data.models

data class GameUiModel(
    val gameId: String = "",
    val firstId: String = "",
    val firstName: String = "",
    val firstHod: Boolean = true,
    val firstSign: String = "0",
    val secondId: String = "",
    val secondName: String = "",
    val secondHod: Boolean = false,
    val secondSign: String = "2",
    val winner: Boolean = false,
    val winnerName: String = "",
    val indexHod: String = "",
    val map: String = "111111111",
    val hasWay: Boolean = false
)