package minesweeper.view

import minesweeper.model.Board
import minesweeper.model.Cell
import minesweeper.model.Cells

class ResultView {
    fun printBoard(board: Board) {
        for (row in 0 until board.height) {
            printRow(board.getRowCells(row))
            println()
        }
    }

    private fun printRow(rowCells: Cells) {
        print(rowCells.cellList.joinToString("") { cell -> buildString { printCell(cell) } })
    }

    private fun printCell(cell: Cell) {
        if (cell.isMine()) {
            print(MINE_SYMBOL)
            return
        }

        print("${cell.mineAroundCount} ")
    }

    companion object {
        private const val MINE_SYMBOL = "* "
    }
}
