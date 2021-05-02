package domain

enum class CoordinateDirection(private val coordinate: (x: Int, y: Int) -> Coordinate) {
    LEFT({ x, y -> Coordinate(x - 1, y) }),
    RIGHT({ x, y -> Coordinate(x + 1, y) }),
    DOWN({ x, y -> Coordinate(x, y + 1) }),
    UP({ x, y -> Coordinate(x, y - 1) }),
    LEFT_UP({ x, y -> Coordinate(x - 1, y - 1) }),
    RIGHT_UP({ x, y -> Coordinate(x + 1, y - 1) }),
    LEFT_DOWN({ x, y -> Coordinate(x - 1, y + 1) }),
    RIGHT_DOWN({ x, y -> Coordinate(x + 1, y + 1) });

    companion object {
        fun getFourWayCoordinates(x: Int, y: Int): Set<Coordinate> {
            return listOf(LEFT, RIGHT, DOWN, UP)
                .map { it.coordinate(x, y) }
                .toSet()
        }

        fun getSurroundingAllCoordinates(x: Int, y: Int): Set<Coordinate> {
            return values()
                .map { it.coordinate(x, y) }
                .toSet()
        }
    }
}
