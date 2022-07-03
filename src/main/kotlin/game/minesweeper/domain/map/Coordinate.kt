package game.minesweeper.domain.map

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x > 0 && y > 0) { "좌표 값은 0보다 커야 합니다. ($x, $y)" }
    }

    fun findBorder(maxHeight: Int, maxWidth: Int) = Direction.values()
        .mapNotNull { it.from(this) }
        .filter { it.x <= maxHeight }
        .filter { it.y <= maxWidth }
}
