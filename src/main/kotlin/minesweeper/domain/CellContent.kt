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

class Cell(val content: CellContent) {
    private var state: CellState = Closed(this)
    val isOpenState: Boolean get() = state is Open

    fun isMine(): Boolean = content is Mine

    fun displayString() = if (isOpenState) content.toDisplayString() else "C"

    fun open() {
        val state = state.open()
        this.state = state
    }

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
