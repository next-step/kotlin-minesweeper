package minesweeper

data class Position(
    val y: Int,
    val x: Int
) {
    operator fun plus(other: Position) = Position(this.y + other.y, this.x + other.x)
}
