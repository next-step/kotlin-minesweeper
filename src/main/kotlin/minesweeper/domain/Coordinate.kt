package minesweeper.domain

class Coordinate private constructor(private val value: Int) {

    init {
        require(value > 0) { "0보다 커야 한다. value: $value" }
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

    companion object {
        private val coordinates = mutableMapOf<Int, Coordinate>()

        fun of(value: Int) = coordinates.getOrPut(value) { Coordinate(value) }
    }
}
