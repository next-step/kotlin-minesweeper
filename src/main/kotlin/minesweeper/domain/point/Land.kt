package minesweeper.domain.point

data class Land(
    val r: Int,
    val c: Int,
) : Point(r, c)
