package minesweeper.domain.position.data

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class PositionsDataSet {
    companion object {
        fun testData(height: Int, width: Int, count: Int): Positions =
            Positions(
                Position.getAllPositionList(height, width).take(count)
            )
    }
}
