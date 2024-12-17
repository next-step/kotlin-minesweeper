package domain.strategy

import domain.Cell
import domain.Coordinate
import domain.MineCount

interface MineCellGenerator {
    fun execute(
        coordinate: Coordinate,
        mineCount: MineCount,
    ): Set<Cell>
}
