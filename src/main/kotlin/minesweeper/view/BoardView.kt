package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell

object BoardView {
    private const val UNOPENED_SYMBOL = "C"

    fun from(board: MineBoard): List<String> {
        val sortedCells = sortCells(board.cells.values.toSet())
        val cellsByRow = sortedCells.groupBy { it.position.row }
        return cellsByRow.map { rowToString(it.value) }
    }

    private fun sortCells(cells: Set<Cell>): List<Cell> =
        cells.toList()
            .sortedWith(
                compareBy<Cell> { it.position.row }
                    .thenBy { it.position.column }
            )

    private fun rowToString(cells: List<Cell>): String =
        cells.joinToString(" ") { cell -> cell.symbol() }

    private fun Cell.symbol() = when (this) {
        is Cell.Mine -> UNOPENED_SYMBOL
        is Cell.Clear -> if (this.isOpened()) this.mineCount.value.toString() else UNOPENED_SYMBOL
    }
}
