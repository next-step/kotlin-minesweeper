package minesweeper.controller

import minesweeper.model.board.Board
import minesweeper.model.board.BoardBuilder
import minesweeper.view.input.InputView
import minesweeper.view.output.OutputView

class MineSweeper(
    private val boardBuilder: BoardBuilder,
    private val inputView: InputView,
    private val outputView: OutputView? = null
) {

    fun run() {
        val board = createMineBoard()
        do {
            guessAndOpen(board)
        } while (!board.isFinished)

        outputView?.printFinalMessage(board)
    }

    private fun createMineBoard(): Board {
        val board = boardBuilder.createNewBoard()
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
