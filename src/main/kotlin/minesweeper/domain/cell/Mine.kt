package minesweeper.domain.cell

data class Mine(
    val x: Int,
    val y: Int
) : Cell(x, y)
