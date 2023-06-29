package minesweeper_refactor.domain.coordinate

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    fun toAroundDirections(aroundDecision: AroundDecision): List<Coordinate> = aroundDecision.decide()
        .map(this::plus)

    operator fun plus(other: Coordinate): Coordinate = Coordinate(x = x + other.x, y = y + other.y)

    override fun compareTo(other: Coordinate): Int = when (val xOrder = this.x compareTo other.x) {
        EQUALS -> this.y compareTo other.y
        else -> xOrder
    }

    companion object {
        private const val EQUALS: Int = 0

        fun createCoordinates(x: Int, yRange: IntRange): List<Coordinate> = yRange.map { y -> Coordinate(x = x, y = y) }
    }
}
