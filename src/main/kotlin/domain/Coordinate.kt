package domain

data class Coordinate(val x: Number, val y: Number) {
    constructor(value: Pair<Int, Int>) : this(Number(value.first), Number(value.second))
}
