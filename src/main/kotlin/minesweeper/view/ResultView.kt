package minesweeper.view

import minesweeper.dto.LandDto

object ResultView {
    private const val GAME_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val LOSE_GAME_MESSAGE = "Lose Game."
    private const val GAME_OVER_MESSAGE = "Game Over. You Win!"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printLineFeed() {
        println()
    }

    fun printLand(landDto: LandDto) {
        val land = landDto.tiles.joinToString("") { tile -> "$tile " }
        val landWithLineFeed = land.chunked(landDto.width * 2)
        landWithLineFeed.forEach { tile -> println(tile) }
    }

    fun printLoseGameMessage() {
        println(LOSE_GAME_MESSAGE)
    }

    fun printGameOverMessage() {
        println(GAME_OVER_MESSAGE)
    }
}
