package minesweeper.domain

private val leftUp = Position(-1, -1)
private val up = Position(-1, 0)
private val rightUp = Position(-1, 1)
private val left = Position(0, -1)
private val right = Position(0, 1)
private val leftDown = Position(1, -1)
private val down = Position(1, 0)
private val rightDown = Position(1, 1)

data class Position(val x: Point, val y: Point) {
    constructor(x: String, y: String) : this(Point(x.toInt()), Point(y.toInt()))
    constructor(x: Int, y: Int) : this(Point(x), Point(y))
    constructor(x: Size, y: Size) : this(Point(x.value), Point(y.value))

    operator fun plus(it: Position): Position {
        return Position(x + it.x, y + it.y)
    }

    fun getAround(): List<Position> {
        return aroundPositions.map {
            this + it
        }
    }

    fun getAdjacent(): List<Position> {
        return adjacentPositions.map {
            this + it
        }
    }

    companion object {
        private val aroundPositions = listOf(leftUp, up, rightUp, left, right, leftDown, down, rightDown)
        private val adjacentPositions = listOf(up, left, right, down)
    }
}
