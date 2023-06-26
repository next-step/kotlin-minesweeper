package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun getEightDirections(): List<Coordinate> = x.generateAroundCoordinates()
        .flatMap(::createAroundCoordinates)
        .filterNot { this == it }

    fun getFourDirections(): List<Coordinate> {
        val horizontalCoordinates = x.generateAroundCoordinates()
            .map { Coordinate(x = it, y = y) }

        val verticalCoordinates = y.generateAroundCoordinates()
            .map { Coordinate(x = x, y = it) }

        return (horizontalCoordinates + verticalCoordinates).filterNot { this == it }
    }

    private fun createAroundCoordinates(x: Int): List<Coordinate> =
        y.generateAroundCoordinates().map { y -> Coordinate(x = x, y = y) }

    private fun Int.generateAroundCoordinates(): List<Int> = (this - AROUND_BLOCK..this + AROUND_BLOCK).toList()

    override fun compareTo(other: Coordinate): Int = when (val xOrder = this.x compareTo other.x) {
        EQUALS -> this.y compareTo other.y
        else -> xOrder
    }

    companion object {
        private const val AROUND_BLOCK: Int = 1
        private const val EQUALS: Int = 0
    }
}
