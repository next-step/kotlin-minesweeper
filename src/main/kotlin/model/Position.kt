package model

data class Position(val x: Int, val y: Int) {

    init {
        require(x >= 0) { "x position must be positive. but provided `$x`" }
        require(y >= 0) { "y position must be positive. but provided `$y`" }
    }

    fun nearByPositions(maxX: Int, maxY: Int): Collection<Position> {
        val xSoughtValues = nearValues(x, maxX)

        return nearValues(y, maxY)
            .flatMap { y -> positions(xSoughtValues, y) }
    }

    private fun nearValues(value: Int, maxValue: Int): Collection<Int> {
        return listOf(value - POSITION_DISTANCE, value, value + POSITION_DISTANCE)
            .filter { (0 <= it) and (it <= maxValue) }
    }

    private fun positions(xSoughtValues: Collection<Int>, y: Int): List<Position> {
        return xSoughtValues.map { x -> Position(x, y) }
            .filter { it != this }
    }

    companion object {
        private const val POSITION_DISTANCE = 1
    }
}
