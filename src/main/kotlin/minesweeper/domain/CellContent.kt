package minesweeper.domain

sealed class CellContent

class Mine : CellContent()

class Empty(val adjacentMines: Int = 0) : CellContent()

class Cell(private val content: CellContent) {
    fun isMine(): Boolean = content is Mine

    override fun toString(): String =
        if (isMine()) {
            MINE_CELL
        } else {
            (content as Empty).adjacentMines.toString()
        }

    companion object {
        private const val MINE_CELL = "*"
    }
}
