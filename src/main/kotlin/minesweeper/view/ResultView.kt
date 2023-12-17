package minesweeper.view

import minesweeper.domain.model.Board
import minesweeper.domain.model.Cell

object ResultView {
    fun drawBoard(board: Board) {
        board.forEach { cells ->
            drawCells(cells)
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
        print("C")
    }
}
