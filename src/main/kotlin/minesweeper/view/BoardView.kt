package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell

object BoardView {
    private const val MINE_SYMBOL = "*"

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
        is Cell.Mine -> MINE_SYMBOL
        is Cell.Clear -> this.mineCount.value.toString()
    }
}
