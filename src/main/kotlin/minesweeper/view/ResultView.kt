package minesweeper.view

import minesweeper.dto.LandDto

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printLineFeed() {
        println()
    }

    fun printLand(landDto: LandDto) {
        val land = landDto.getTiles().joinToString("") { tile -> "$tile " }
        val landWithLineFeed = land.chunked(landDto.getWidth() * 2)
        landWithLineFeed.forEach { tile -> println(tile) }
    }
}
