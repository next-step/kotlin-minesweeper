package minesweeper.view

import minesweeper.domain.model.Board
import minesweeper.domain.model.cell.Cell

object ResultView {
    fun drawBoard(board: Board) {
        board.forEach { rowCells ->
            drawCells(rowCells)
            println()
        }
    }

    private fun drawCells(cells: List<Cell>) {
        cells.forEach { cell -> drawCell(cell) }
    }

    private fun drawCell(cell: Cell) {
        if (cell.isMine()) {
            print("*")
            return
        }
        print("${cell.getCountOfMinesNearby()}")
    }
}
