package minesweeper.domain

class Positions(
    private val positions: Set<Position> = emptySet()
) {
    val size
        get() = positions.size

    fun getValues(): Set<Position> = positions

    infix fun containSamePosition(otherPositions: Positions): Boolean = positions.intersect(otherPositions.getValues()).isNotEmpty()

    operator fun plus(position: Position): Positions = Positions(this.positions + position)
    operator fun plus(positions: Positions): Positions = Positions(this.positions + positions.positions)
    operator fun minus(position: Position): Positions = Positions(this.positions - position)
    operator fun minus(positions: Positions): Positions = Positions(this.positions - positions.positions)
}

fun Set<Position>.toPositions(): Positions = Positions(this)
