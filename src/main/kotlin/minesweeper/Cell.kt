package minesweeper

sealed class Cell(val position: Position) {
    abstract val neighborMineCount: Int

    open fun determineCell(determineMineCount: Int) {}

    var isOpen: Boolean = false
        private set

    val x: Int
        get() = position.x

    data class MineCell(val pos: Position) : Cell(pos) {
        override val neighborMineCount: Int = 0
    }

    data class NumberCell(val pos: Position, private var _mineCount: Int = 0) : Cell(pos) {
        override val neighborMineCount: Int
            get() = _mineCount

        override fun determineCell(determineMineCount: Int) {
            _mineCount = determineMineCount
        }
    }

    fun availableOpen(): Boolean {
        return !isOpen
    }

    fun isMine(): Boolean {
        return this is MineCell
    }

    fun matchRowIndex(rowIndex: Int): Boolean {
        return position.y == rowIndex
    }

    fun open() {
        if (isOpen) return // 이미 열린 경우 무시

        isOpen = true
    }

    companion object {
        fun createMine(position: Position): Cell {
            return MineCell(position)
        }

        fun createDefault(position: Position): Cell {
            return NumberCell(position)
        }
    }
}
