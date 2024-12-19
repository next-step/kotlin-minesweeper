package view

import domain.Cell
import domain.Cells

object OutputView {
    fun showMineSweeperBoard(cells: Cells) {
        val cellsByRow = cells.cells.groupBy { it.coordinate.r.value }

        cellsByRow.toSortedMap().forEach { (_, rowCells) ->
            val sortedRow = rowCells.sortedBy { it.coordinate.c.value } // 열 기준 정렬
            sortedRow.forEach { cell ->
                when (cell) {
                    is Cell.MineCell -> print(MINE_CELL)
                    is Cell.EmptyCell -> print(EMPTY_CELL)
                }
            }
            println()
        }
    }

    private const val MINE_CELL = "* "
    private const val EMPTY_CELL = "C "
}
