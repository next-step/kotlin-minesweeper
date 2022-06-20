package minesweeper.domain.common

data class Position(
    val x: PositiveInt,
    val y: PositiveInt
) {
    companion object {
        fun of(x: Int, y: Int): Position {
            return Position(PositiveInt(x), PositiveInt(y))
        }
    }
}
