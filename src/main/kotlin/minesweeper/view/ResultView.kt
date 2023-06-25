package minesweeper.view

import minesweeper.view.model.BoardView
import minesweeper.view.model.MatchStateView

object ResultView {

    private const val GAME_START_MESSAGE: String = "\n지뢰찾기 게임 시작"

    fun printGameStartMessage() = println(message = GAME_START_MESSAGE)


    fun printCurrentMinesweeperBoard(boardView: BoardView) = boardView.forEach { _, rows ->
        println(message = rows.toString())
    }

    fun printMatchState(matchStateView: MatchStateView) = println(message = matchStateView.message)
}
