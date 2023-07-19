package step4.domain.state

import step4.domain.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

class Lose : MinesweeperState {
    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState =
        throw IllegalStateException("지뢰를 설치할 수 있는 상태가 아닙니다.")

    override fun open(coordinate: Coordinate): MinesweeperState = throw IllegalStateException("종료 상태에선 cell을 열 수 없습니다.")
}
