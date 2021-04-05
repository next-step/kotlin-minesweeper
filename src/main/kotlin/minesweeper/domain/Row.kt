package minesweeper.domain

import minesweeper.domain.MineBoard.Companion.MINE

class Row(
    width: Int
) {
    private val row: Array<String> = Array<String>(width) { " C " }

    fun getColumnValue(column: Int): String {
        return row[column]
    }

    private fun isDuplicateMine(column: Int): Boolean {
        return row[column] == MINE
    }

    fun deploy(column: Int): Boolean {
        if (!isDuplicateMine(column)) {
            row[column] = MINE
            return true
        }
        return false
    }
}
