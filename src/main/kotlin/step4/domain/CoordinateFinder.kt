package step4.domain

enum class CoordinateFinder(
    val find: (Coordinate) -> Coordinate,
) {
    SOUTH({ it.down() }),
    SOUTH_WEST({ it.down().left() }),
    WEST({ it.left() }),
    NORTH_WEST({ it.up().left() }),
    NORTH({ it.up() }),
    NORTH_SOUTH({ it.up().right() }),
    EAST({ it.right() }),
    SOUTH_EAST({ it.down().right() }),
}
