package minesweeper.entity

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x는 0보다 커야합니다." }
        require(y >= 0) { "y는 0보다 커야합니다." }
    }

    fun adjacentCoordinates(): List<Coordinate> {
        return Direction.entries
            .filter { direction ->
                val newX = x + direction.deltaX
                val newY = y + direction.deltaY
                newX >= 0 && newY >= 0
            }
            .map { direction ->
                Coordinate(x + direction.deltaX, y + direction.deltaY)
            }
    }

    fun isWithinBounds(
        width: Width,
        height: Height,
    ): Boolean {
        return x in 0 until width.value && y in 0 until height.value
    }

    companion object {
        fun generateCoordinates(
            height: Height,
            width: Width,
        ): List<Coordinate> {
            return List(height.value * width.value) {
                Coordinate(it % width.value, it / width.value)
            }
        }
    }
}
