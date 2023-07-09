package minesweeper.view

import minesweeper.domain.board.Cell
import minesweeper.domain.board.CellBoard

object OutputView {
    private const val mineSymbol = "*"
    private const val nonMineSymbol = "C"
    fun printMineBoard(board: CellBoard) {
        for (cells in board.board) {
            printCells(cells)
            println()
        }
    }

    private fun printCells(cells: List<Cell>) {
        for (cell in cells) {
            val symbol = if (cell.isMineActive()) mineSymbol else nonMineSymbol
            print("$symbol ")
        }
    }
}
