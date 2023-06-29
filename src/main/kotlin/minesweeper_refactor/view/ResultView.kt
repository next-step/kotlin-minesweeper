package minesweeper_refactor.view

import minesweeper_refactor.view.model.BoardView
import minesweeper_refactor.view.model.MatchStateView

object ResultView {

    private const val GAME_START_MESSAGE: String = "\n지뢰찾기 게임 시작"

    fun printGameStartMessage() = println(message = GAME_START_MESSAGE)

    fun printCurrentMinesweeperBoard(boardView: BoardView) = boardView.forEach { _, rows ->
        println(message = rows.toString())
    }

    fun printMatchState(matchStateView: MatchStateView) = println(message = matchStateView.message)
}
