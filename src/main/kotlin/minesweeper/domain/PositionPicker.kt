package minesweeper.domain

import minesweeper.domain.cell.Position

interface PositionPicker {
    fun pick(allPositions: Set<Position>): Set<Position>
}
