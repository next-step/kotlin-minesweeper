package minesweeper.domain

sealed class Cell {

    abstract val position: Position
    abstract val isOpen: Boolean

    abstract fun isMine(): Boolean
    abstract fun open(): Cell

    fun aroundPositions(): List<Position> = position.aroundPositionList()

    fun isRowStartCell(): Boolean = position.isRowStart()

    data class MineCell(override val position: Position, override val isOpen: Boolean = false) : Cell() {
        override fun isMine(): Boolean = true
        override fun open(): MineCell = MineCell(position, true)
    }

    data class SafetyCell(override val position: Position, val mineCount: Int, override val isOpen: Boolean) : Cell() {
        override fun isMine(): Boolean = false
        fun isNotContainAroundMine(): Boolean = mineCount == 0
        override fun open(): SafetyCell = SafetyCell(position, mineCount, true)
    }
}
