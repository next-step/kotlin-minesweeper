package minesweeper.domain

object RandomPositionSelector : PositionSelector {
    override fun select(positions: Set<Position>, selectNum: Int): Set<Position> {
        return positions
            .shuffled()
            .take(selectNum)
            .toSet()
    }
}
