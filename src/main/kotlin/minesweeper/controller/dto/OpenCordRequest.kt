package minesweeper.controller.dto

import minesweeper.domain.MapCord

data class OpenCordRequest(
    val value: String
) {
    fun toMapCord(): MapCord {
        val splitWord = value.replace(" ", "")
            .split(",")
        return MapCord(splitWord[0].toInt(), splitWord[1].toInt())
    }
}
