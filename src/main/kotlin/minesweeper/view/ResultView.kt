package minesweeper.view

import minesweeper.dto.GameResultDto

interface ResultView {
    fun printMinesweeperGameStartMessage()

    fun printMineBoard(gameResult: GameResultDto)

    fun printMinesweeperGameResult(gameResult: GameResultDto)
}
