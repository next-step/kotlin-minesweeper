package minesweeper.domain.block

data class Position(val x: Int, val y: Int) {

    fun moveTo(other: Position): Position {
        return Position(x + other.x, y + other.y)
    }
}
