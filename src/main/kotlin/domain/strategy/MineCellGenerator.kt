package domain.strategy

import domain.BoardHeight
import domain.BoardWidth
import domain.Cell
import domain.MineCount

interface MineCellGenerator {
    fun execute(
        height: BoardHeight,
        width: BoardWidth,
        mineCount: MineCount,
    ): Set<Cell>
}
