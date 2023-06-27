package minesweeper_refactor.domain.coordinate

data class Coordinate(private val x: Int, private val y: Int) : Comparable<Coordinate> {

    fun toAroundDirections(aroundDecision: AroundDecision): List<Coordinate> = aroundDecision.decide()
        .map(this::plus)

    operator fun plus(other: Coordinate): Coordinate = Coordinate(x = x + other.x, y = y + other.y)

    override fun compareTo(other: Coordinate): Int = when (val xOrder = this.x compareTo other.x) {
        EQUALS -> this.y compareTo other.y
        else -> xOrder
    }

    companion object {
        private const val EQUALS: Int = 0
    }
}
