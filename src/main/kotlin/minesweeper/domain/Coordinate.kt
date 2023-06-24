package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {

    fun aroundCoordinates(): List<Coordinate> = x.aroundCoordinates()
        .flatMap(::createAroundCoordinates)
        .filter { this != it }

    private fun createAroundCoordinates(x: Int): List<Coordinate> =
        y.aroundCoordinates().map { y -> Coordinate(x = x, y = y) }

    private fun Int.aroundCoordinates(): List<Int> = listOf(this, this - AROUND_BLOCK, this + AROUND_BLOCK)

    operator fun compareTo(other: Coordinate): Int = compareValuesBy(
        a = this,
        b = other,
        { x },
        { y },
    )

    companion object {
        private const val AROUND_BLOCK: Int = 1
    }
}
