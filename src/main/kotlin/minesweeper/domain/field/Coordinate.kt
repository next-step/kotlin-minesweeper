package minesweeper.domain.field

data class Coordinate(
    val x: CoordinateValue,
    val y: CoordinateValue
) {
    fun findAround(): List<Coordinate> = Direction.values().map { Coordinate(x + it.x, y + it.y) }

    private enum class Direction(val x: CoordinateValue, val y: CoordinateValue) {
        EAST(CoordinateValue.plus, CoordinateValue.stay),
        WEST(CoordinateValue.minus, CoordinateValue.stay),
        SOUTH(CoordinateValue.stay, CoordinateValue.plus),
        NORTH(CoordinateValue.stay, CoordinateValue.minus),
        NORTH_EAST(CoordinateValue.plus, CoordinateValue.minus),
        NORTH_WEST(CoordinateValue.minus, CoordinateValue.minus),
        SOUTH_EAST(CoordinateValue.plus, CoordinateValue.plus),
        SOUTH_WEST(CoordinateValue.minus, CoordinateValue.plus);
    }
}
