package minesweeper

sealed class Cell2(val position: Position) {
    abstract val isMine: Boolean
    abstract val mineCount: Int

    val x: Int
        get() = position.x

    open fun determineCell(cells: Cells) {}

    fun matchRowIndex(rowIndex: Int): Boolean {
        return position.y == rowIndex
    }

    data class MineCell(val pos: Position) : Cell2(pos) {
        override val isMine: Boolean = true
        override val mineCount: Int = 0
    }

    data class NumberCell(val pos: Position, private var _mineCount: Int = 0) : Cell2(pos) {
        override val isMine: Boolean = false
        override val mineCount: Int
            get() = _mineCount

        override fun determineCell(cells: Cells) {
            _mineCount = cells.neighborsMineCount(position)
        }
    }

    companion object {
        fun createMine(position: Position): Cell2 {
            return MineCell(position)
        }

        fun createDefault(position: Position): Cell2 {
            return NumberCell(position)
        }
    }
}