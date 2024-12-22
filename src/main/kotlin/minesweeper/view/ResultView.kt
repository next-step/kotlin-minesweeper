package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

object ResultView {
    private const val BLANK_EXPRESSION = " "
    private const val MINE_EXPRESSION = "*"
    private const val NORMAL_EXPRESSION = "C"

    fun printBoard(
        board: Board,
        lastX: Int,
    ) {
        board.cells.forEach { printBoard(it, lastX) }
    }

    private fun printBoard(
        cell: Cell,
        lastX: Int,
    ) {
        if (cell.isLastX(lastX)) {
            println(printExpression(cell))
        }
        if (!cell.isLastX(lastX)) {
            print(printExpression(cell))
            print(BLANK_EXPRESSION)
        }
    }

    private fun printExpression(cell: Cell): String {
        if (cell.hasMine) {
            return MINE_EXPRESSION
        }
        return NORMAL_EXPRESSION
    }
}
