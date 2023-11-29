package domain

class Coordinates(private val value: Set<Coordinate>) : Set<Coordinate> by value {
    constructor(vararg values: Coordinate) : this(values.toSet())

    override operator fun contains(element: Coordinate): Boolean {
        return value.any { it.x == element.x && it.y == element.y }
    }
}
