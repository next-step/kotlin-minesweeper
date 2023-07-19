package step4.domain.state

import step4.domain.Cells
import step4.domain.strategy.CoordinateSelectStrategy

class Ready(
    var toFindCellCount: Int,
    val cells: Cells,
) : MinesweeperState {
    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState {
        cells.installMine(mineCount, coordinateSelectStrategy)
        return Running()
    }
}
