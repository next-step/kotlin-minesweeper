package step4.domain

import step4.domain.state.MinesweeperState
import step4.domain.strategy.CoordinateSelectStrategy

class MinesweeperGame(
    var state: MinesweeperState,
    val strategy: CoordinateSelectStrategy,
) {
    fun installMines(mineCount: Int) {
        state = state.installMine(mineCount, strategy)
    }

    fun open(coordinate: Coordinate) {
        state = state.open(coordinate)
    }
}
