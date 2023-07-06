package minesweeper.view

import minesweeper.domain.BoardRow
import minesweeper.domain.CellType
import minesweeper.domain.MineBoard

object ResultView {
    fun printBoard(board: MineBoard) {
        for (row in board.boardInfo) {
            printRow(row)
            println()
        }
    }

    private fun printRow(row: BoardRow) {
        for (cell in row.rowInfo) {
            print(
                when (cell.type) {
                    CellType.MINE -> "* "
                    CellType.EMPTY -> "C "
                }
            )
        }
    }
}
