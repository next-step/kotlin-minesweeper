package model

data class Positions(private val positions: Set<Position>) : Set<Position> by positions {
    constructor(vararg positions: Position) : this(positions.toSet())

    constructor(positions: List<Position>) : this(positions.toSet())
}
