package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark

object BoardView {
    private const val MINE_SYMBOL = "*"
    private const val EMPTY_SYMBOL = "C"

    fun from(board: MineBoard): List<String> {
        val sortedCells = sortCells(board.cells)
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
        cells.joinToString(" ") { cell -> cell.mark.symbol() }

    private fun CellMark.symbol() = when (this) {
        CellMark.MINE -> MINE_SYMBOL
        else -> EMPTY_SYMBOL
    }
}
