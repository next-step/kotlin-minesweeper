package minesweeper.domain.position.data

import minesweeper.domain.position.Positions

class PositionsDataSet {
    companion object {
        private const val DEFAULT_TEST_DATA_POSITION_COUNT = 100

        fun testData(
            count: Int = DEFAULT_TEST_DATA_POSITION_COUNT,
            rowIntRange: IntRange,
            colIntRange: IntRange
        ): Positions =
            Positions(PositionDataSet.testData(count, rowIntRange, colIntRange))
    }
}
