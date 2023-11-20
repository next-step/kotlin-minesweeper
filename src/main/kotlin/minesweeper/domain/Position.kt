package minesweeper.domain

data class Position(val x: Int, val y: Int) {

    fun match(otherPosition: Position): Boolean {
        return this == otherPosition
    }

    operator fun plus(otherPosition: Position): Position {
        return Position(x + otherPosition.x, y + otherPosition.y)
    }
}
