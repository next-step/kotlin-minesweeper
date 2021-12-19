package mine_tdd.cell

import mine_tdd.cell.Position.Companion.findNearPosition

abstract class Cell(val position: Position) {
    fun findNearPosition(): List<Position> = position.findNearPosition()
}
