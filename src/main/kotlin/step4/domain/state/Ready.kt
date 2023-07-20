package step4.domain.state

import step4.domain.CellInfo
import step4.domain.cell.Cells
import step4.domain.coordinate.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

class Ready(
    var toFindCellCount: Int,
    val cells: Cells,
) : MinesweeperState {
    init {
        require(toFindCellCount > MINIMUM_TO_FIND_CELL_COUNT) { "찾아야하는 cell 갯수가 ${MINIMUM_TO_FIND_CELL_COUNT}이 될 수 없습니다." }
    }

    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState {
        cells.installMine(mineCount, coordinateSelectStrategy)
        return Running(toFindCellCount - mineCount, cells)
    }

    override fun open(coordinate: Coordinate): MinesweeperState = throw IllegalStateException("게임 시작전에 cell을 열 수 없습니다.")

    override fun isFinished(): Boolean = false

    override fun cellInfos(): List<CellInfo> = cells.cellInfos()

    companion object {
        private const val MINIMUM_TO_FIND_CELL_COUNT = 0
    }
}
