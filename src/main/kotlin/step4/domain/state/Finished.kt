package step4.domain.state

import step4.domain.Cells
import step4.domain.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

abstract class Finished : MinesweeperState {

    final override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState =
        throw IllegalStateException("지뢰를 설치할 수 있는 상태가 아닙니다.")

    final override fun open(coordinate: Coordinate): MinesweeperState = throw IllegalStateException("종료 상태에선 cell을 열 수 없습니다.")

    final override fun isFinished(): Boolean = true
}

class Win(
    val cells: Cells,
) : Finished()

class Lose(
    val cells: Cells,
) : Finished()
