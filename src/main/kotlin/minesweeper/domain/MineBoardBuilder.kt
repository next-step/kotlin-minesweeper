package minesweeper.domain

class MineBoardBuilder {
    private lateinit var rows: RowCount
    private lateinit var columns: ColumnCount
    private lateinit var mines: MineCount

    fun rows(rowCount: RowCount) {
        rows = rowCount
    }

    fun columns(columnCount: ColumnCount) {
        columns = columnCount
    }

    fun mines(mineCount: MineCount) {
        mines = mineCount
    }

    fun build(): MineBoard {
        return MineBoard(rows, columns, mines)
    }
}
