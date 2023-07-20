package step4.domain.state

import step4.domain.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

interface MinesweeperState {
    fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState

    fun open(coordinate: Coordinate): MinesweeperState

    fun isFinished(): Boolean
}
