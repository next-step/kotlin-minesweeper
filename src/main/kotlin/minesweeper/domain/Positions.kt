package minesweeper.domain

class Positions(
    val positions: Array<Array<Position?>>,
) {
    val rows: Int = positions.size
    val cols: Int = positions[0].size

    fun position(rows: Int, cols: Int): Position {
        val position = positions[rows][cols]
        return position ?: Position(0)
    }

    fun updatePositionValue(rows: Int, cols: Int, value: Int) {
        positions[rows][cols] = Position(value)
    }
}
