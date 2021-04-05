package minesweeper.domain.position

class Coordinate private constructor(
    private val value: Int
) : Comparable<Coordinate> {

    init {
        require(value >= Minimum_Value) { "최소 $Minimum_Value 이어야 한다. value: $value" }
    }

    operator fun plus(value: Int): Coordinate? {
        return if (value + this.value < Minimum_Value) {
            null
        } else {
            of(this.value + value)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Coordinate) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "Coordinate(value=$value)"
    }

    override fun compareTo(other: Coordinate) = value.compareTo(other.value)

    companion object {
        private const val Minimum_Value = 1

        private val coordinates = mutableMapOf<Int, Coordinate>()

        fun of(value: Int) = coordinates.getOrPut(value) { Coordinate(value) }
    }
}
