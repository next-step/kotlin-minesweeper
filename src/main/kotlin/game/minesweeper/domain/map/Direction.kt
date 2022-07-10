package game.minesweeper.domain.map

enum class Direction(private val moveX: Int, private val moveY: Int) {
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    BOTTOM_LEFT(1, -1),
    LEFT(0, -1),
    TOP_LEFT(-1, -1),
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    ;

    fun from(here: Coordinate): Coordinate? = runCatching {
        Coordinate(here.x + moveX, here.y + moveY)
    }.getOrNull()
}
