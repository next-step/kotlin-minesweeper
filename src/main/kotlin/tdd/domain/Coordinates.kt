package tdd.domain

class Coordinates(
    private val coordinates: List<Coordinate>
) : List<Coordinate> by coordinates {
    companion object {
        private const val MIN_ROW = 0
        private const val MIN_COL = 0

        fun all(height: Int, width: Int): Coordinates {
            return Coordinates(
                (MIN_ROW until height).flatMap { row ->
                    (MIN_COL until width).map { col -> Coordinate(row, col) }
                }
            )
        }

        fun around(coordinate: Coordinate): Coordinates {
            return Coordinates(
                listOf(
                    coordinate.upLeft(),
                    coordinate.up(),
                    coordinate.upRight(),
                    coordinate.left(),
                    coordinate.right(),
                    coordinate.downLeft(),
                    coordinate.down(),
                    coordinate.downRight(),
                )
            )
        }
    }
}
