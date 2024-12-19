package domain

import domain.strategy.MineCellGenerator
import domain.strategy.RandomMineCellGenerator

class MineBoard(
    private val coordinate: Coordinate,
    private val mineCount: MineCount,
    private val mineCellGenerator: MineCellGenerator = RandomMineCellGenerator(),
) {
    init {
        require(coordinate.height * coordinate.width >= mineCount.value) {
            "$INVALID_MINE_COUNT. ${coordinate.height} x ${coordinate.width} must be greater than $mineCount"
        }
    }

    fun create(): Cells {
        val mineCoordinates = mineCellGenerator.execute(coordinate, mineCount).map { it.coordinate }.toSet()

        return Cells.generateWithMines(
            coordinate.height.value,
            coordinate.width.value,
            mineCoordinates = mineCoordinates,
        )
    }

    companion object {
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
