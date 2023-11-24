package domain

class Board(private val height: Int, private val width: Int) {
    private val cells: Array<Array<Boolean>> = Array(height) { Array(width) { false } }

    fun placeMineAt(position: Position) {
        cells[position.y][position.x] = true
    }

    fun countMines(): Int = cells.sumOf { row -> row.count { it } }
}
