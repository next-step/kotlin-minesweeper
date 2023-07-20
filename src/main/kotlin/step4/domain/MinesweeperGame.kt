package step4.domain

import step4.domain.cell.Cells
import step4.domain.coordinate.Coordinate
import step4.domain.state.MinesweeperState
import step4.domain.state.Ready
import step4.domain.strategy.CoordinateRandomSelectStrategy
import step4.domain.strategy.CoordinateSelectStrategy

class MinesweeperGame(
    val height: Int,
    var state: MinesweeperState,
    val strategy: CoordinateSelectStrategy = CoordinateRandomSelectStrategy(),
) {
    init {
        require(height > 0) { "높이는 0보다 커야합니다." }
    }

    fun installMines(mineCount: Int): MinesweeperGame {
        state = state.installMine(mineCount, strategy)
        return this
    }

    fun open(coordinate: Coordinate) {
        state = state.open(coordinate)
    }

    fun isFinished(): Boolean = state.isFinished()

    companion object {
        fun createNewGame(height: Int, width: Int): MinesweeperGame {
            return MinesweeperGame(
                height = height,
                state = Ready(
                    toFindCellCount = height * width,
                    cells = Cells(List(height * width) { index -> parseToCoordinate(index, width) }),
                ),
            )
        }

        private fun parseToCoordinate(index: Int, width: Int): Coordinate {
            val row = index / width
            val column = index % width
            return Coordinate(row = row, column = column)
        }
    }
}
