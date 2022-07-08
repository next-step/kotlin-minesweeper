package minesweeper.dto

import minesweeper.model.Cell
import minesweeper.model.Cells
import minesweeper.model.MineBoard

class MineBoardRowDto(
    val boardRow: List<String>
) : List<String> by boardRow {

    companion object {
        private const val MINE_MARK = "*"
        private const val CLOSE_MARK = "C"

        fun of(cells: Cells, board: MineBoard): MineBoardRowDto = MineBoardRowDto(cells.cells.map { findMarkOf(it, board) })

        private fun findMarkOf(cell: Cell, board: MineBoard): String {
            if (cell.isOpenedMine) {
                return MINE_MARK
            }

            if (cell.isOpened) {
                return cell.findSurroundingMineCountSum(board).toString()
            }

            return CLOSE_MARK
        }
    }
}
