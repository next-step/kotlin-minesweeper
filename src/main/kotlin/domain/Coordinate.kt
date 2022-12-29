package domain

data class Coordinate(val x: Row, val y: Column) {
    constructor(value: Pair<Int, Int>) : this(Row(value.first), Column(value.second))
}
