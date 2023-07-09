package minesweeper.domain

class Coordinate(
    val x: Int,
    val y: Int,
) {

    fun checkWithinBounds(maxHeight: Int, maxWidth: Int): Boolean {
        val checkX = x in 0 until maxWidth
        val checkY = y in 0 until maxHeight
        return checkX && checkY
    }

    companion object {
        private val CACHE_COORDINATES = cache()
        private const val CACHE_X: Int = 20
        private const val CACHE_Y: Int = 20

        private fun cache(): Map<Pair<Int, Int>, Coordinate> {
            val coordinates = mutableMapOf<Pair<Int, Int>, Coordinate>()

            for (i in 0 until CACHE_X) {
                for (j in 0 until CACHE_Y) {
                    val coordinate = Coordinate(i, j)
                    coordinates[Pair(i, j)] = coordinate
                }
            }

            return coordinates
        }

        fun of(x: Int, y: Int): Coordinate {
            val key = Pair(x, y)
            return CACHE_COORDINATES[key] ?: Coordinate(x, y)
        }

        fun origin(): Coordinate = of(0, 0)
    }
}
