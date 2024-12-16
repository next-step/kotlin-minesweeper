package minesweeper.domain

data class Coordinate(
    val y: Int,
    val x: Int,
) {
    val neighbors: List<Coordinate> by lazy { neighbors() }

    init {
        require(y >= 0) { "y축 좌표는 0 이상이어야 합니다." }
        require(x >= 0) { "x축 좌표는 0 이상이어야 합니다." }
    }

    private fun neighbors(): List<Coordinate> =
        Direction
            .entries
            .mapNotNull { next(it) }

    private fun next(direction: Direction): Coordinate? {
        if (y + direction.dy < 0 || x + direction.dx < 0) {
            return null
        }
        return Coordinate(y + direction.dy, x + direction.dx)
    }
}
