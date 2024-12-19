package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.strategy.MineCellGenerator
import domain.strategy.RandomMineCellGenerator

class MineBoard(
    private val coordinate: Coordinate,
    private val mineCount: MineCount,
    private val mineCellGenerator: MineCellGenerator = RandomMineCellGenerator(),
) {
    init {
        require(coordinate.height * coordinate.width >= mineCount.value) { INVALID_MINE_COUNT }
    }

    fun create(): Cells {
        val mineCoordinates = mineCellGenerator.execute(coordinate, mineCount).map { it.coordinate }.toSet()

        return Cells.generateWithMines(
            heightRange = MINIMUM_HEIGHT..coordinate.height.value,
            widthRange = MINIMUM_WIDTH..coordinate.width.value,
            mineCoordinates = mineCoordinates,
        )
    }

    companion object {
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
