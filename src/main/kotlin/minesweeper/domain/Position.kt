package minesweeper.domain

data class Position(val x: Int, val y: Int) {

    fun match(otherPosition: Position): Boolean {
        return this == otherPosition
    }

    operator fun plus(otherPosition: Position): Position {
        return Position(x + otherPosition.x, y + otherPosition.y)
    }

    operator fun compareTo(otherPosition: Position): Int {
        return when {
            y > otherPosition.y -> 1
            y < otherPosition.y -> -1
            else -> x.compareTo(otherPosition.x)
        }
    }
}
