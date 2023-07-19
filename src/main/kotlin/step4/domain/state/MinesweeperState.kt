package step4.domain.state

import step4.domain.strategy.CoordinateSelectStrategy

interface MinesweeperState {
    fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState
}
