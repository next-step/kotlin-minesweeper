package minesweeper.controller

import minesweeper.model.BoardBuilder
import minesweeper.view.input.InputView
import minesweeper.view.output.OutputView

class MineSweeper(
    private val boardBuilder: BoardBuilder,
    private val inputView: InputView,
    private val outputView: OutputView? = null
) {

    fun run() {
        val board = boardBuilder.createNewBoard()
        outputView?.printInitialMessage()
        outputView?.printBoard(board)

        do {
            val positionToOpen = inputView.postionToOpen(board)
            board.openCell(positionToOpen)
            outputView?.printBoard(board)
        } while (!board.isFinished)

        outputView?.printFinalMessage(board)
    }
}
