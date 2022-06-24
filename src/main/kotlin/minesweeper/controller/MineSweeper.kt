package minesweeper.controller

import minesweeper.model.board.Board
import minesweeper.model.board.BoardGenerator
import minesweeper.view.input.InputView
import minesweeper.view.output.OutputView

class MineSweeper(
    private val boardGenerator: BoardGenerator,
    private val inputView: InputView,
    private val outputView: OutputView? = null
) {

    fun run() {
        val board = createBoard()
        do {
            guessAndOpen(board)
        } while (!board.isFinished)
    }

    private fun createBoard(): Board {
        val board = boardGenerator.createBoard()
        outputView?.printInitialMessage()
        outputView?.printBoard(board)
        return board
    }

    private fun guessAndOpen(board: Board) {
        val coordinateToOpen = inputView.coordinateToOpen(board)
        board.openCell(coordinateToOpen)
        outputView?.printBoard(board)
    }
}
