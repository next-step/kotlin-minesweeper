package domain

class Coordinates(private val value: Set<Coordinate>) : Set<Coordinate> by value {
    constructor(vararg values: Coordinate) : this(values.toSet())
}
