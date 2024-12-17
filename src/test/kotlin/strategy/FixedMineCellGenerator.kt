package strategy

import domain.BoardHeight
import domain.BoardWidth
import domain.Cell
import domain.Coordinate
import domain.MineCount
import domain.strategy.MineCellGenerator

class FixedMineCellGenerator : MineCellGenerator {
    override fun execute(
        coordinate: Coordinate,
        mineCount: MineCount,
    ): Set<Cell> {
        return setOf(
            Cell.MineCell(Coordinate(BoardHeight(1), BoardWidth(1))),
            Cell.MineCell(Coordinate(BoardHeight(1), BoardWidth(2))),
        )
    }
}
