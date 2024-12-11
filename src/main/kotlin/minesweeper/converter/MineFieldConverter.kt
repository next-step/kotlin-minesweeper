package minesweeper.converter

import minesweeper.entity.Cell
import minesweeper.entity.Coordinate
import minesweeper.entity.MineField

class MineFieldConverter {
    fun mapToViewData(mineField: MineField): List<List<Char>> {
        return List(mineField.height.value) { y ->
            List(mineField.width.value) { x ->
                val cell = mineField.findCell(Coordinate(x, y))
                convertCellToView(cell, mineField)
            }
        }
    }

    private fun convertCellToView(
        cell: Cell,
        mineField: MineField,
    ): Char {
        return if (cell.isRevealed) {
            when (cell) {
                is Cell.Mine -> MINE_VIEW
                is Cell.Empty -> mineField.countAroundMines(cell.coordinate).digitToChar()
            }
        } else {
            HIDDEN_VIEW
        }
    }

    companion object {
        const val MINE_VIEW = '*'
        const val HIDDEN_VIEW = '?'
    }
}
