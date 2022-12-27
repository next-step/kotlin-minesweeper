package minesweeper.domain.position.vendor

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import minesweeper.util.cartesianProduct

class PositionsVendor {
    fun randomPositions(
        rowIndexRange: IntRange,
        colIndexRange: IntRange,
        totalCount: Int = rowIndexRange.count() * colIndexRange.count()
    ): Positions {
        val requiredTotalCountRange = (0..rowIndexRange.count() * colIndexRange.count())

        require(totalCount in requiredTotalCountRange) {
            "totalCount should be in range $requiredTotalCountRange"
        }

        return Positions(
            listOf(rowIndexRange, colIndexRange).cartesianProduct()
                .shuffled()
                .take(totalCount)
                .map { Position(it[0], it[1]) }
        )
    }
}
