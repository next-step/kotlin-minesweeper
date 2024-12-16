package minesweeper.view

import minesweeper.model.Cell

/**
 * @author 이상준
 */
class ResultView {
    fun printBoard(cells: List<Cell>) {
        cells.groupBy { it.row }.forEach { (_, rowCells) ->
            rowCells.sortedBy { it.column }.forEach { cell ->
                printCell(cell)
            }
            println()
        }
    }

    private fun printCell(cell: Cell) {
        if (cell.isMine()) {
            print(MINE_SYMBOL)
            return
        }

        print("${cell.mineAroundCount()} ")
    }

    companion object {
        private const val MINE_SYMBOL = "* "
    }
}
