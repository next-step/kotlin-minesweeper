package minesweeper.ui

import minesweeper.domain.Board
import minesweeper.domain.Cell

object BoardPrinter {
    const val MINE_CELL = "*"
    const val CLEAR_CELL = "C"
    const val BETWEEN_CELL = " "
    fun print(board: Board) {
        println("지뢰찾기 게임 시작")

        board.cells.forEach { cell ->
            if (cell.mine) print(MINE_CELL) else print(CLEAR_CELL)
            if (isLastCell(board.width, cell)) println() else print(BETWEEN_CELL)
        }
    }

    private fun isLastCell(width: Int, cell: Cell): Boolean = (cell.point.x + 1) % width == 0
}
