package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Coordinate
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
    var coordinate = Coordinate(0, 0)
    while (board.isPlaying) {
        coordinate = InputView.inputCoordinate(board)
        board.open(coordinate)
        ResultView.resultBoard(board, width - 1)
    }
    return board.isPlayerWin(board.findPoint(coordinate.x, coordinate.y)!!)
}
