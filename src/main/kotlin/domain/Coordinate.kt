package domain

data class Coordinate(val x: Number, val y: Number) {
    constructor(value: Pair<Int, Int>) : this(Number(value.first), Number(value.second))

    fun movedCoordinate(directions: Directions): Coordinate {
        return Coordinate((this.x + directions.coordinate.x).value to (this.y + directions.coordinate.y).value)
    }
}
