package minesweeper.domain.point

import minesweeper.Direction
import minesweeper.common.ZERO

class Land(
    r: Int,
    c: Int,
    mines: Mines,
    private var isOpen: Boolean = false,
) : Point(r, c) {
    val aroundMineCount: Int

    init {
        aroundMineCount = countAroundMines(r, c, mines)
    }

    override fun isMine(): Boolean = false

    fun open() {
        isOpen = true
    }

    fun isOpened(): Boolean = isOpen

    private fun countAroundMines(
        row: Int,
        col: Int,
        mines: Mines,
    ): Int =
        Direction.entries.filter {
            row + it.dy >= ZERO && col + it.dx >= ZERO
        }.count {
            mines.contains(Mine(row + it.dy, col + it.dx))
        }
}
