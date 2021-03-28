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

    fun getRounds(): List<Position> {
        val rounds = mutableListOf<Position>()

        (-1..1).forEach { roundX ->
            (-1..1).forEach { roundY ->
                if (roundX + x.value >= 0 && roundY + y.value >= 0) {
                    rounds.add(Position(roundX + x.value, roundY + y.value))
                }
            }
        }
        return rounds
    }
}
