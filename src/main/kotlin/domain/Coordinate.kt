package domain

data class Coordinate(
    val y: Pos,
    val x: Pos
) {
    constructor(y: Int, x: Int) : this(Pos.of(y), Pos.of(x))
}
