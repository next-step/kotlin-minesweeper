package minesweeper.domain

object RandomPositionSelector : PositionSelector {
    override fun select(positions: Positions, selectNum: Int): Positions {
        return positions
            .shuffled()
            .take(selectNum)
            .toSet()
            .toPositions()
    }
}
