package minesweeper.domain.cell

data class Empty(
    val x: Int,
    val y: Int
) : Cell(x, y)
