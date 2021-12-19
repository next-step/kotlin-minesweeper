package mine_tdd.cell

import mine_tdd.cell.Position.Companion.findNearPosition

abstract class Cell(val position: Position, val nearMineCount: Int = DEFAULT_MINE_COUNT) {
    fun findNearPosition(): List<Position> = position.findNearPosition()

    companion object {
        private const val DEFAULT_MINE_COUNT = -1
    }
}
