package minesweeper.dto

import minesweeper.model.Cell
import minesweeper.model.Cells
import minesweeper.model.MineBoard

class BoardRowDto(
    val boardRow: List<String>
) : List<String> by boardRow {

    val countOfCloseMark
        get() = boardRow.count { it == CLOSE_MARK }

    fun containsMine() = boardRow.contains(MINE_MARK)

    companion object {
        private const val MINE_MARK = "*"
        private const val CLOSE_MARK = "C"

        fun of(cells: Cells, board: MineBoard): BoardRowDto = BoardRowDto(cells.cells.map { findMarkOf(it, board) })

        private fun findMarkOf(cell: Cell, board: MineBoard): String {
            if (cell.isMineAndOpened) {
                return MINE_MARK
            }

            if (cell.isOpened) {
                return cell.findSurroundingMineCountSum(board).toString()
            }

            return CLOSE_MARK
        }
    }
}
