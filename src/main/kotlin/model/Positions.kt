package model

data class Positions(private val positions: Set<Position>) {

    val size: Int
        get() = positions.size

    operator fun contains(position: Position): Boolean {
        return position in positions
    }

    fun forEach(function: (Position) -> Unit) {
        positions.forEach(function)
    }

    constructor(vararg positions: Position) : this(positions.toSet())

    constructor(positions: List<Position>) : this(positions.toSet())
}
