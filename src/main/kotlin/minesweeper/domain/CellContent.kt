package minesweeper.domain

sealed class CellContent
class Mine : CellContent()
class Empty : CellContent()

class Cell(private val content: CellContent) {
    fun isMine(): Boolean = content is Mine
    override fun toString(): String = if (isMine()) MINE_CELL else NON_MINE_CELL

    companion object {
        private const val MINE_CELL = "*"
        private const val NON_MINE_CELL = "C"
    }
}