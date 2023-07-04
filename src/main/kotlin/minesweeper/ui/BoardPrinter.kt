package minesweeper.ui

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Point

object BoardPrinter {
    const val MINE_CELL = "*"
    const val CLEAR_CELL = "C"
    const val BETWEEN_CELL = " "
    fun print(board: Board) {
        println("지뢰찾기 게임 시작")

        val points = Point.square(board.height, board.width)
        val cells = board.cells
        points.forEach { point ->
            val cell = cells.at(point)
            if (cell.mine) print(MINE_CELL) else print("${cell.count}")
            if (isLastCell(board.width, cell)) println() else print(BETWEEN_CELL)
        }
    }

    private fun isLastCell(width: Int, cell: Cell): Boolean = (cell.point.x + 1) % width == 0
}
