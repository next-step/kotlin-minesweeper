package minesweeper

enum class Direction(val offset: Position) {
    UP_LEFT(Position(-1, -1)),
    UP(Position(-1, 0)),
    UP_RIGHT(Position(-1, 1)),
    LEFT(Position(0, -1)),
    RIGHT(Position(0, 1)),
    DOWN_LEFT(Position(1, -1)),
    DOWN(Position(1, 0)),
    DOWN_RIGHT(Position(1, 1));

    companion object {
        fun neighbors(position: Position): List<Position> {
            return entries.map { direction ->
                Position(position.x + direction.offset.x, position.y + direction.offset.y)
            }
        }
    }
}
