package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Coordinates
import minesweeper.domain.strategy.RandomMineDeployStrategy

class MineBoard(
    val cells: List<Cell>,
) {
    init {
        require(cells.isNotEmpty()) { "지뢰판은 빌 수 없습니다." }
    }

    companion object {
        fun createWithRandomStrategy(
            height: Int,
            width: Int,
            mineCount: Int,
        ): MineBoard {
            validateArguments(height, width, mineCount)

            val coordinates = Coordinates.from(height = height, width = width)
            val mineCoordinates =
                RandomMineDeployStrategy.execute(coordinates = coordinates, mineCount = mineCount)

            return MineBoard(cells = coordinates.mineAt(mineCoordinates))
        }

        private fun validateArguments(height: Int, width: Int, numberOfMine: Int) {
            require(height > 0) { "지뢰판 높이는 1보다 작을 수 없습니다." }
            require(width > 0) { "지뢰판 너비는 1보다 작을 수 없습니다." }
            require(numberOfMine >= 0) { "지뢰 개수는 음수일 수 없습니다." }
            require(height * width >= numberOfMine) { "지뢰판 넓이보다 지뢰 개수 많을 수 없습니다." }
        }
    }
}
