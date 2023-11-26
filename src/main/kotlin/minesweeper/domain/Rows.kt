package minesweeper.domain

data class Rows(val rows: List<Row>) {

    fun at(row: Int, col: Int): Cell {
        return rows[row].cells[col]
    }
}
