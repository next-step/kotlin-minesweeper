package minesweeper.domain

class Board(
    val cells: Map<Coordinate, Cell>,
) {
    init {
        require(cells.isNotEmpty()) { "빈 판을 생성할 수 없다" }
    }

    fun get(
        y: Int,
        x: Int,
    ): Cell? = cells[Coordinate(y, x)]

    fun countMines(
        y: Int,
        x: Int,
    ): Int =
        Coordinate(y, x)
            .neighbors
            .count { cells[it] is MinedCell }
}
