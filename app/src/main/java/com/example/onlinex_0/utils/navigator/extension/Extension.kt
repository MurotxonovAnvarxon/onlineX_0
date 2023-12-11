package com.example.onlinex_0.utils.navigator.extension

import com.example.onlinex_0.data.common.GameUiCommon


fun GameUiCommon.setNewMap(newMap: String): GameUiCommon =
    GameUiCommon(
        gameId,
        firstId,
        firstName,
        firstHod,
        firsSign,
        secondId,
        secondName,
        secondHod,
        secondSign,
        winner,
        winnerName,
        indexHod,
        newMap,
        !hasWay
    )

fun GameUiCommon.setWinner(name: String): GameUiCommon =
    GameUiCommon(
        gameId,
        firstId,
        firstName,
        firstHod,
        firsSign,
        secondId,
        secondName,
        secondHod,
        secondSign,
        true,
        name,
        indexHod,
        map,
        hasWay
    )