package tdd.minesweeper.ui

import tdd.minesweeper.domain.Board
import tdd.minesweeper.domain.GameState

interface OutputView {
    fun printGameStarted()

    fun displayBoard(board: Board)

    fun printGameEnded(state: GameState)

    fun printError(errorMessage: String)
}
