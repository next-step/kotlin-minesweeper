package model

import model.MineCreator.createMine

private const val MINE_SIGN = "*"

class Cells(val rows: Int, val cols: Int, val mineCount: Int) {
    var cells: Array<Array<Cell>>

    init {
        val mines = createMine(rows, cols, mineCount, mutableSetOf())
        cells = createCells(rows, cols, mines)
    }

    private fun createCells(rows: Int, cols: Int, mines: Set<Location>) =
        Array(rows) { row -> createCell(cols, mines, row) }

    private fun createCell(
        cols: Int,
        mines: Set<Location>,
        row: Int
    ) = Array(cols) { col -> cellType(mines, row, col) }

    private fun cellType(mines: Set<Location>, row: Int, col: Int): Cell = when {
        mines.contains(Location(row, col)) -> Cell(MINE_SIGN)
        else -> Cell()
    }
}
