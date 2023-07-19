package step4.domain.state

import step4.domain.Cells
import step4.domain.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

class Ready(
    var toFindCellCount: Int,
    val cells: Cells,
) : MinesweeperState {
    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState {
        cells.installMine(mineCount, coordinateSelectStrategy)
        return Running(toFindCellCount - mineCount, cells)
    }

    override fun open(coordinate: Coordinate): MinesweeperState = throw IllegalStateException("게임 시작전에 cell을 열 수 없습니다.")
}
