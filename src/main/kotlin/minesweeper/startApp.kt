package minesweeper

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView
import kotlin.system.exitProcess

fun main() {
    try {
        startGame()
    } catch (e: Exception) {
        println(e.message)
        exitProcess(0)
    }
}

fun startGame() {
    val length = InputView.inputLength()
    val width = InputView.inputWidth()
    val mines = InputView.inputMines()
    val board = Board(width, length, mines)
    ResultView.resultGame(playingGame(board, width))
}

fun playingGame(board: Board, width: Int): Boolean {
    while (board.isPlaying) {
        val coordinate = InputView.inputCoordinate()
        board.openPoint(coordinate)
        ResultView.resultBoard(board, width - 1)
    }
    return board.isPlayerWin()
}
