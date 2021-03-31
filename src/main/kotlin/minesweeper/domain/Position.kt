package minesweeper.domain

internal data class Position(val x: NaturalNumber, val y: NaturalNumber) : Comparable<Position> {

    constructor(x: Int, y: Int) : this(NaturalNumber(x), NaturalNumber(y))

    init {
        require(x >= NaturalNumber.ZERO && y >= NaturalNumber.ZERO)
    }

    override fun compareTo(other: Position): Int {
        val yCompare = this.y.compareTo(other.y)
        if (yCompare != 0) {
            return yCompare
        }
        return this.x.compareTo(other.x)
    }

    val around: List<Position>
        get() {
            return PositionRound.values().mapNotNull { it.createRound(this) }
        }

    private enum class PositionRound(val x: Int, val y: Int) {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1),
        LEFT_UP(-1, -1),
        LEFT_DOWN(-1, 1),
        RIGHT_UP(1, -1),
        RIGHT_DOWN(1, 1);

        fun createRound(position: Position): Position? {
            val retX = x + position.x.value
            val retY = y + position.y.value

            if (retX < 0 || retY < 0) {
                return null
            }

            return Position(retX, retY)
        }
    }
}
