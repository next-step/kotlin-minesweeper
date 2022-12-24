package model

import model.MineCreator.createMine

private const val MINE_SIGN = "*"

class Cells(val rows: Int, val cols: Int, val mineCount: Int) {
    var cells: Array<Array<Cell>>

    init {
        val mines = createMine(rows, cols, mineCount, mutableSetOf())
        cells = createCell(rows, cols, mines)
    }

    private fun createCell(rows: Int, cols: Int, mines: Set<Location>) =
        Array(rows) { row -> createCols(cols, mines, row) }

    private fun createCols(
        cols: Int,
        mines: Set<Location>,
        row: Int
    ) = Array(cols) { col -> creatCell(mines, row, col) }

    private fun creatCell(mines: Set<Location>, row: Int, col: Int): Cell = when {
        mines.contains(Location(row, col)) -> Cell(MINE_SIGN)
        else -> Cell()
    }
}
