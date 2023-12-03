package minesweeper.domain.support

import minesweeper.domain.PositionSelector
import minesweeper.domain.Positions
import minesweeper.domain.toPositions

object FixedPositionSelector : PositionSelector {
    override fun select(positions: Positions, selectNum: Int): Positions {
        val values = positions.getValues()
        return values
            .sortedWith(compareBy({ it.y }, { it.x }))
            .take(selectNum)
            .toSet()
            .toPositions()
    }
}
