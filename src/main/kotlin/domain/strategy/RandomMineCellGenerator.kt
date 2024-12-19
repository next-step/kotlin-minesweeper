package domain.strategy

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell
import domain.Cell.MineCell
import domain.Col
import domain.Coordinate
import domain.Row
import kotlin.random.Random

class RandomMineCellGenerator : MineCellGenerator {
    override fun execute(
        coordinate: Coordinate,
        mineCount: Int,
    ): Set<Cell> =
        generateSequence {
            val randomHeight = Random.nextInt(MINIMUM_HEIGHT, coordinate.r.value)
            val randomWidth = Random.nextInt(MINIMUM_WIDTH, coordinate.c.value)
            MineCell(Coordinate(Row(randomHeight), Col(randomWidth)))
        }.distinct()
            .take(mineCount)
            .toSet()
}
