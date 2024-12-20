package domain

enum class Direction(val rowOffset: Int, val colOffset: Int) {
    NORTH(-1, 0),
    SOUTH(1, 0),
    WEST(0, -1),
    EAST(0, 1),
    NORTH_WEST(-1, -1),
    NORTH_EAST(-1, 1),
    SOUTH_WEST(1, -1),
    SOUTH_EAST(1, 1),
}
