package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.MineBoard

object OutputView {
    private const val mineSymbol = "*"
    private const val nonMineSymbol = "C"

    fun printMineBoard(board: MineBoard) {
        for (cells in board.board) {
            printCells(cells)
            println()
        }
    }

    private fun printCells(cells: List<Cell>) {
        for (cell in cells) {
            val symbol = if (cell.hasMine) mineSymbol else nonMineSymbol
            print("$symbol ")
        }
    }
}
