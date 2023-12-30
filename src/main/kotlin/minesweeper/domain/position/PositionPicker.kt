package minesweeper.domain.position

interface PositionPicker {
    fun pick(allPositions: Set<Position>, count: Int): Set<Position>
}
