package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun getAroundCoordinates(): List<Coordinate> = x.generateAroundCoordinates()
        .flatMap(::createAroundCoordinates)
        .filterNot { this == it }

    private fun createAroundCoordinates(x: Int): List<Coordinate> =
        y.generateAroundCoordinates().map { y -> Coordinate(x = x, y = y) }

    private fun Int.generateAroundCoordinates(): List<Int> = listOf(this, this - AROUND_BLOCK, this + AROUND_BLOCK)

    override fun compareTo(other: Coordinate): Int = when (val xOrder = this.x compareTo other.x) {
        EQUALS -> this.y compareTo other.y
        else -> xOrder
    }

    companion object {
        private const val AROUND_BLOCK: Int = 1
        private const val EQUALS: Int = 0
    }
}
