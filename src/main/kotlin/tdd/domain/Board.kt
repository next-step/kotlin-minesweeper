package tdd.domain

class Board(
    val cells: Map<Coordinate, Cell>
) {
    companion object {
        private const val MIN_ROW = 0
        private const val MIN_COL = 0

        fun of(height: Int, width: Int): Board {
            val coordinateToCells = (MIN_ROW until height).flatMap { row ->
                (MIN_COL until width).map { col -> Coordinate(row, col) to Cell() }
            }
            return Board(mapOf(*coordinateToCells.toTypedArray()))
        }
    }
}
