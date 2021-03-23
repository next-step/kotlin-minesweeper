package minesweeper.view

import minesweeper.domain.Cell

class CellView(private val cell: Cell) {
    override fun toString(): String {
        if (!cell.open) {
            return CLOSED
        }
        if (cell.exploded) {
            return EXPLOSION
        }
        if (cell.bomb) {
            return BOMB
        }
        return String.format("%2d", cell.count)
    }

    companion object {
        const val CLOSED = "⬜"
        const val EXPLOSION = "\uD83D\uDCA5"
        const val BOMB = "💣"
    }
}
