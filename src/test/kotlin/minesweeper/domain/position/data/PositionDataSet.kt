package minesweeper.domain.position.data

import minesweeper.domain.position.Position
import minesweeper.util.cartesianProduct

class PositionDataSet {
    companion object {
        private val DEFAULT_INDEX_RANGE: IntRange = (0..10)

        fun testData(
            rowIntRange: IntRange = DEFAULT_INDEX_RANGE,
            colIntRange: IntRange = DEFAULT_INDEX_RANGE
        ): Position =
            Position(testIndex(rowIntRange), testIndex(colIntRange))

        fun testData(
            count: Int,
            rowIntRange: IntRange = DEFAULT_INDEX_RANGE,
            colIntRange: IntRange = DEFAULT_INDEX_RANGE
        ): List<Position> =
            listOf(rowIntRange, colIntRange).cartesianProduct()
                .take(count)
                .map { Position(it[0], it[1]) }

        private fun testIndex(indexRange: IntRange = DEFAULT_INDEX_RANGE): Int = indexRange.random()
    }
}
