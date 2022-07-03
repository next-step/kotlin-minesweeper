package game.minesweeper.domain.map

enum class Direction(private val moveX: Int, private val moveY: Int) {
    East(0, 1),
    Southeast(1, 1),
    South(1, 0),
    Southwest(1, -1),
    West(0, -1),
    Northwest(-1, -1),
    North(-1, 0),
    Northeast(-1, 1),
    ;

    fun from(here: Coordinate) = runCatching {
        Coordinate(here.x + moveX, here.y + moveY)
    }.getOrNull()
}
