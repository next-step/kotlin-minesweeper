package domain

sealed class Cell {
    abstract val id: CellId
    private var _isOpen: Boolean = false
    val isOpen: Boolean
        get() = _isOpen

    abstract fun addNumberHint(
        row: Int,
        column: Int,
        allCells: Cells,
    ): Cell

    open fun isMine(): Boolean = false

    open fun open(
        row: Int,
        column: Int,
        allCells: Cells,
    ): List<Position> {
        if (_isOpen) return emptyList()
        _isOpen = true
        return listOf(Position(row, column))
    }

    data object Empty : Cell() {
        override val id = CellId.EMPTY

        override fun addNumberHint(
            row: Int,
            column: Int,
            allCells: Cells,
        ): Cell {
            val adjacentMineCount = Directions.countMatching(row, column, allCells) { it.isMine() }
            return if (adjacentMineCount > 0) NumberCell(adjacentMineCount) else this
        }
    }

    data object MineCell : Cell() {
        override val id = CellId.MINE

        override fun isMine(): Boolean = true

        override fun addNumberHint(
            row: Int,
            column: Int,
            allCells: Cells,
        ): Cell = this
    }

    data class NumberCell(val count: Int) : Cell() {
        override val id = CellId.NUMBER

        override fun addNumberHint(
            row: Int,
            column: Int,
            allCells: Cells,
        ): Cell = this
    }

    companion object {
        fun create(isMine: Boolean): Cell = if (isMine) MineCell else Empty
    }
}
