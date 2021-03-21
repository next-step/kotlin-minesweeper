package minesweeper.view

import minesweeper.domain.Cell

class CellView(private val cell: Cell) {
    override fun toString(): String {
        if (cell.bomb) {
            return BOMB
        }
        if (cell.count > 0) {
            return cell.count.toString()
        }
        return BLANK
    }

    companion object {
        const val BLANK = "◻️"
        const val BOMB = "💣"
    }
}
