package domain.strategy

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.BoardHeight
import domain.BoardWidth
import domain.Cell
import domain.Cell.MineCell
import domain.Coordinate
import domain.MineCount
import kotlin.random.Random

class RandomMineCellGenerator : MineCellGenerator {
    override fun execute(
        coordinate: Coordinate,
        mineCount: MineCount,
    ): Set<Cell> =
        generateSequence {
            val randomHeight = Random.nextInt(MINIMUM_HEIGHT, coordinate.height.value)
            val randomWidth = Random.nextInt(MINIMUM_WIDTH, coordinate.width.value)
            MineCell(Coordinate(BoardHeight(randomHeight), BoardWidth(randomWidth)))
        }.distinct()
            .take(mineCount.value)
            .toSet()
}
