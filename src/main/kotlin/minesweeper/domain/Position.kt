package minesweeper.domain

data class Position(val x: Point, val y: Point) {
    constructor(x: Int, y: Int) : this(Point(x), Point(y))
    constructor(x: Size, y: Size) : this(Point(x.value), Point(y.value))

    operator fun plus(it: Position): Position {
        return Position(x + it.x, y + it.y)
    }
}
