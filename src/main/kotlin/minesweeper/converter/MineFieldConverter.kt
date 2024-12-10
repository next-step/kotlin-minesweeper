package minesweeper.converter

import minesweeper.entity.Cell
import minesweeper.entity.MineField

class MineFieldConverter {
    fun mapToViewData(mineField: MineField): List<List<Char>> {
        val result = MutableList(mineField.height.value) { MutableList(mineField.width.value) { EMPTY_VIEW } }

        for (cell in mineField.cells) {
            val (x, y) = cell.coordinate
            result[y][x] = if (cell is Cell.Mine) MINE_VIEW else EMPTY_VIEW
        }

        return result
    }

    companion object {
        const val MINE_VIEW = '*'
        const val EMPTY_VIEW = 'o'
    }
}
