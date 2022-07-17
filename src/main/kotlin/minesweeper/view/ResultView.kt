package minesweeper.view

import minesweeper.dto.MineBoardDto
import minesweeper.model.GameStatus

interface ResultView {
    fun printMinesweeperGameStartMessage()

    fun printMineBoard(gameResult: MineBoardDto)

    fun printMinesweeperGameStatus(gameStatus: GameStatus)
}
