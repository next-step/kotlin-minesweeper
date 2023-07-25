package minesweeper.domain

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardMeta
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

class Minesweeper(
    private val input: InputView,
    private val output: OutputView,
) {
    fun start() {
        val width = input.getWidth()
        val height = input.getHeight()
        val mineCount = input.getMineCount()

        val boardMeta = BoardMeta(width, height, mineCount)
        val board = Board.create(boardMeta)
        output.printStart(board)
    }
}
