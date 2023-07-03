package domain

class Coordinates(
    private val coordinates: List<Coordinate>,
) : List<Coordinate> by coordinates {
    companion object {
        private val aroundCoordinates = listOf(
            Coordinate().up().left(),
            Coordinate().up(),
            Coordinate().up().right(),
            Coordinate().left(),
            Coordinate().right(),
            Coordinate().down().left(),
            Coordinate().down(),
            Coordinate().down().right(),
        )

        fun around(coordinate: Coordinate): List<Coordinate> {
            return aroundCoordinates.map { coordinate + it }
        }

        fun all(height: Int, width: Int): Coordinates {
            return Coordinates(
                (Coordinate.ROW_START_POSITION until height).flatMap { row ->
                    (Coordinate.COL_START_POSITION until width).map { col -> Coordinate(row, col) }
                },
            )
        }
    }
}
