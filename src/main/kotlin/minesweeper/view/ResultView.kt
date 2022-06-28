package minesweeper.view

import minesweeper.model.GameResult

interface ResultView {
    fun printMinesweeperGameStartMessage()

    fun printMineBoard(gameResult: GameResult)

    fun printMinesweeperGameResult(gameResult: GameResult)
}
