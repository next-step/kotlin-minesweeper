package minesweeper.domain.support

import minesweeper.domain.Position
import minesweeper.domain.PositionSelector

object FixedPositionSelector : PositionSelector {
    override fun select(positions: Set<Position>, selectNum: Int): Set<Position> {
        return positions
            .sortedWith(compareBy({ it.y }, { it.x }))
            .take(selectNum)
            .toSet()
    }
}
