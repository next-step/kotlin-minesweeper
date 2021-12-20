package mine_tdd.cell

import mine_tdd.cell.Position.Companion.findNearPosition

abstract class Cell(val position: Position, val nearMineCount: Int = DEFAULT_MINE_COUNT, var isOpen: Boolean = false) {
    fun findNearPosition(): List<Position> = position.findNearPosition()
    fun open() {
        isOpen = true
    }

    companion object {
        private const val DEFAULT_MINE_COUNT = -1
    }
}
