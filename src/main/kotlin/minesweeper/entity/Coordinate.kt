package minesweeper.entity

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x는 0보다 커야합니다." }
        require(y >= 0) { "y는 0보다 커야합니다." }
    }

    fun adjacentCoordinates(): List<Coordinate> {
        val directions = listOf(-1, 0, 1)
        return directions.flatMap { deltaX ->
            getAdjacentCoordinatesForRow(deltaX, directions)
        }
    }

    private fun getAdjacentCoordinatesForRow(
        deltaX: Int,
        directions: List<Int>,
    ): List<Coordinate> {
        return directions.mapNotNull { deltaY ->
            when {
                deltaX == 0 && deltaY == 0 -> null
                else -> {
                    val adjacentX = x + deltaX
                    val adjacentY = y + deltaY
                    if (adjacentX >= 0 && adjacentY >= 0) {
                        Coordinate(adjacentX, adjacentY)
                    } else {
                        null
                    }
                }
            }
        }
    }

    fun isWithinBounds(
        width: Int,
        height: Int,
    ): Boolean {
        return x in 0 until width && y in 0 until height
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
