package minesweeper.view

import minesweeper.domain.GridBoard

private const val ANNOUNCE_GAME_START = "지뢰 찾기 게임 시작"

object ResultView {

    fun showBoard(board: GridBoard) {
        println("\n$ANNOUNCE_GAME_START")
        print(board.joinToString())
    }
}
