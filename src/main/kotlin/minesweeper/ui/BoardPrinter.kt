package minesweeper.ui

import minesweeper.domain.board.Board
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell

object BoardPrinter {
    const val MINE_CELL = "*"
    const val NOT_OPENED_CELL = "C"
    const val BETWEEN_CELL = " "

    fun print(board: Board) {
        board.cells.forEach { cell ->
            printCell(cell)
            if (isLastCell(board.width, cell)) println() else print(BETWEEN_CELL)
        }
    }

    private fun printCell(cell: Cell) = when {
        !cell.isOpened -> print(NOT_OPENED_CELL)
        cell is MineCell -> print(MINE_CELL)
        else -> print("${cell.count}")
    }

    private fun isLastCell(width: Int, cell: Cell): Boolean = (cell.point.x + 1) % width == 0
}
