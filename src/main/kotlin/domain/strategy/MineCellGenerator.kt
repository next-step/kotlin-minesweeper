package domain.strategy

import domain.Cell
import domain.Coordinate

interface MineCellGenerator {
    fun execute(
        coordinate: Coordinate,
        mineCount: Int,
    ): Set<Cell>
}
