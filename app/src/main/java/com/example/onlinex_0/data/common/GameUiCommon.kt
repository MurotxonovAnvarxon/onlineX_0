package com.example.onlinex_0.data.common

data class GameUiCommon(
    val gameId: String,
    val firstId: String,
    val firstName: String,
    val firstHod: Boolean,
    val firsSign: String,
    val secondId: String,
    val secondName: String,
    val secondHod: Boolean,
    val secondSign: String,
    val winner: Boolean,
    val winnerName: String,
    val indexHod: String,
    val map: String,
    val hasWay: Boolean = true
)