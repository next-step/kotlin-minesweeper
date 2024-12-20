package view

import domain.Cell
import domain.MineBoard

object OutputView {
    fun showMineSweeperBoard(board: MineBoard) {
        val cells = board.cells
        val cellsByRow = cells.cells.groupBy { it.coordinate.row.value }

        cellsByRow.toSortedMap().forEach { (_, rowCells) ->
            val sortedRow = rowCells.sortedBy { it.coordinate.col.value } // 열 기준 정렬
            sortedRow.forEach { cell ->
                when (cell) {
                    is Cell.MineCell -> print(MINE_CELL)
                    is Cell.EmptyCell -> print("${board.countAdjacentMines(cell)} ")
                }
            }
            println()
        }
    }

    private const val MINE_CELL = "* "
}
