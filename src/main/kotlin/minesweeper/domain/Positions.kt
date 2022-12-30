package minesweeper.domain

data class Positions(
    val positions: List<Position>,
) {
    constructor(height: Int, width: Int) : this((0 until height).flatMap { row ->
        (0 until width).map { column ->
            Position(row, column)
        }
    })

    fun getRandoms(count: Int) = positions.shuffled().take(count)
    fun all() = positions
}
