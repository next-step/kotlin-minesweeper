package domain

class Coordinates(
    private val coordinates: List<Coordinate>,
) : List<Coordinate> by coordinates {
    companion object {
        private val neighborCoordinates = listOf(
            Coordinate.origin().up().left(),
            Coordinate.origin().up(),
            Coordinate.origin().up().right(),
            Coordinate.origin().left(),
            Coordinate.origin().right(),
            Coordinate.origin().down().left(),
            Coordinate.origin().down(),
            Coordinate.origin().down().right(),
        )

        fun neighbors(coordinate: Coordinate): List<Coordinate> {
            return neighborCoordinates.map { coordinate + it }
        }

        fun all(height: Int, width: Int): Coordinates {
            return Coordinates(
                (Coordinate.ROW_START_POSITION until height).flatMap { row ->
                    (Coordinate.COL_START_POSITION until width).map { col -> Coordinate(row, col) }
                }
            )
        }
    }
}
