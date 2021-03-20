package minesweeper.view

import minesweeper.domain.Cell

class CellView(private val cell: Cell) {
    override fun toString(): String {
        return if (cell.bomb) BOMB else BLANK
    }

    companion object {
        const val BLANK = "◻️"
        const val BOMB = "💣"
    }
}
