package minesweeper.domain

@JvmInline
value class Position(private val value: Int) : Comparable<Position> {

    override fun compareTo(other: Position): Int =
        this.value.compareTo(other.value)
}
