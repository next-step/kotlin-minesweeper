package minesweeper.model.board

import minesweeper.model.cell.Island
import minesweeper.model.cell.Mine
import minesweeper.model.cell.Opening

class Minefield(
    private val rows: Rows,
    private val cols: Cols,
    val minefield: Array<Array<Opening>>
) {
    constructor(rows: Int, cols: Int) : this(
        rows = Rows(rows),
        cols = Cols(cols),
        minefield = Array(rows) { Array(cols) { Island() } }
    )

    constructor(rows: Int, cols: Int, minefield: Array<Array<Opening>>) : this(
        rows = Rows(rows),
        cols = Cols(cols),
        minefield = minefield
    )

    fun plantingMine(countOfMine: Int) {
        var mineCount = 0

        while (mineCount < countOfMine) {
            val row = rows.getPosition()
            val col = cols.getPosition()

            mineCount = plantingResult(row, col, mineCount)
        }
    }

    private fun plantingResult(row: Int, col: Int, mineCount: Int): Int {
        if (minefield[row][col] is Island) {
            minefield[row][col] = Mine()
            return mineCount + 1
        }
        return mineCount
    }
}
