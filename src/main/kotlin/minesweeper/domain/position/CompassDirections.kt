package minesweeper.domain.position

enum class CompassDirections(val x: Int, val y: Int) {
    NORTH(0, 1),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1);

    companion object {
        fun of(position: Position): Positions =
            Positions.of(
                values().map {
                    Position.of(position.x + it.x, position.y + it.y)
                }
            )
    }
}
