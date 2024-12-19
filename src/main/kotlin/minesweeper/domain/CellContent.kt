package minesweeper.domain

sealed class CellContent {
    abstract fun toDisplayString(): String
}

class Mine : CellContent() {
    override fun toDisplayString(): String {
        return MINE_CELL
    }

    companion object {
        private const val MINE_CELL = "*"
    }
}

class Empty(val adjacentMines: Int = 0) : CellContent() {
    override fun toDisplayString(): String {
        return adjacentMines.toString()
    }
}

class Cell(private val content: CellContent) {
    fun isMine(): Boolean = content is Mine

    fun displayString() = content.toDisplayString()

    companion object {
        fun from(
            isMinePosition: Boolean,
            adjacentMines: Int = 0,
        ): Cell {
            return if (isMinePosition) {
                Cell(Mine())
            } else {
                Cell(Empty(adjacentMines))
            }
        }
    }
}
