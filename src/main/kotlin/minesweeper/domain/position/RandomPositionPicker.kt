package minesweeper.domain.position

class RandomPositionPicker : PositionPicker {
    override fun pick(allPositions: Set<Position>, count: Int): Set<Position> =
        allPositions.shuffled().take(count).toSet()
}
