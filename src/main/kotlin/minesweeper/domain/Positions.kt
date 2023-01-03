package minesweeper.domain

data class Positions(
    val values: List<Position>,
) {
    constructor(height: Int, width: Int) : this((0 until height).flatMap { row ->
        (0 until width).map { column ->
            Position(row, column)
        }
    })
}
