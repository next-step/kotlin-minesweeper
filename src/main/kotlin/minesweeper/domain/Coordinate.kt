package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {

    init {
        require(x >= START_INDEX && y >= START_INDEX) {
            "좌표 값은 $START_INDEX 이상이어야 합니다"
        }
    }

    fun adjacentCoordinates(): List<Coordinate> {
        val xAdjacentCoordinates = aroundRange(x).map { Coordinate(it, y) }
        val yAdjacentCoordinates = aroundRange(y).map { Coordinate(x, it) }

        return (xAdjacentCoordinates + yAdjacentCoordinates).filterNot { it == this }
    }

    fun aroundCoordinates(): List<Coordinate> {
        return aroundRange(x).flatMap { aroundCoordinates(it) }
            .filterNot { it == this }
    }

    private fun aroundCoordinates(x: Int): List<Coordinate> {
        return aroundRange(y).map { Coordinate(x, it) }
    }

    private fun aroundRange(index: Int): IntRange {
        val maxAroundIndex = index + AROUND_INDEX

        return if (index == START_INDEX) {
            index..maxAroundIndex
        } else {
            (index - AROUND_INDEX)..maxAroundIndex
        }
    }

    companion object {
        private const val START_INDEX = 0
        private const val AROUND_INDEX = 1

        fun listOf(height: Int, width: Int): List<Coordinate> {
            return (START_INDEX until height).flatMap { y ->
                coordinates(width, y)
            }
        }

        private fun coordinates(width: Int, y: Int): List<Coordinate> {
            return (START_INDEX until width).map { x ->
                Coordinate(x, y)
            }
        }
    }
}
