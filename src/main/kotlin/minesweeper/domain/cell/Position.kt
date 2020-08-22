package minesweeper.domain.cell

data class Position(
    val x: Int,
    val y: Int
)

fun Pair<Int, Int>.toPosition() = Position(first, second)
