package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {

    init {
        require(x >= START_INDEX && y >= START_INDEX) {
            "좌표 값은 $START_INDEX 이상이어야 합니다"
        }
    }

    fun nearCoordinate(): List<Coordinate> {
        return nearRange(x).flatMap { nearCoordinate(it) }
            .filterNot { it == this }
    }

    private fun nearCoordinate(x: Int): List<Coordinate> {
        return nearRange(y).map { Coordinate(x, it) }
    }

    private fun nearRange(index: Int): IntRange {
        val maxNearIndex = index + NEAR_INDEX

        return if (index == START_INDEX) {
            index..maxNearIndex
        } else {
            index - NEAR_INDEX..maxNearIndex
        }
    }

    companion object {
        private const val START_INDEX = 0
        private const val NEAR_INDEX = 1

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
