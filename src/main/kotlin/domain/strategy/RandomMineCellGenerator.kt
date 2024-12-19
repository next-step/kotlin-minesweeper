package domain.strategy

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell
import domain.Cell.MineCell
import domain.Col
import domain.Coordinate
import domain.MineGameMetric
import domain.Row
import kotlin.random.Random

class RandomMineCellGenerator : MineCellGenerator {
    override fun execute(mineGameMetric: MineGameMetric): Set<Cell> {
        val mineCells = mutableSetOf<Cell>()

        while (mineCells.size < mineGameMetric.mineCount) {
            val randomHeight = Random.nextInt(MINIMUM_HEIGHT, mineGameMetric.boardHeightSize + 1)
            val randomWidth = Random.nextInt(MINIMUM_WIDTH, mineGameMetric.boardWidthSize + 1)

            mineCells.add(MineCell(Coordinate(Row(randomHeight), Col(randomWidth))))
        }

        return mineCells
    }
}
