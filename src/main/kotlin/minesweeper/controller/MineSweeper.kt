package minesweeper.controller

import minesweeper.model.BoardBuilder
import minesweeper.view.output.OutputView

class MineSweeper(
    private val boardBuilder: BoardBuilder,
    private val outputView: OutputView? = null
) {

    fun run() {
        val board = boardBuilder.createNewBoard()
        outputView?.printInitialMessage()
        outputView?.printBoard(board)
    }
}
