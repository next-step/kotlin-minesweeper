package domain.strategy

import domain.Cell
import domain.MineGameMetric

interface MineCellGenerator {
    fun execute(mineGameMetric: MineGameMetric): Set<Cell>
}
