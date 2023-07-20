package step4.domain.state

import step4.domain.cell.Cells
import step4.domain.coordinate.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

class Running(
    var toFindCellCount: Int,
    val cells: Cells,
) : MinesweeperState {
    init {
        require(toFindCellCount > 0) { "찾아야하는 cell 갯수가 0이 될 수 없습니다." }
    }

    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState =
        throw IllegalStateException("지뢰를 설치할 수 있는 상태가 아닙니다.")

    override fun open(coordinate: Coordinate): MinesweeperState {
        val cellOpenResult = cells.open(coordinate)
        if (cellOpenResult == 0) {
            return Lose(cells)
        }
        toFindCellCount -= cellOpenResult
        return confirmWin()
    }

    override fun isFinished(): Boolean = false

    private fun confirmWin(): MinesweeperState {
        if (toFindCellCount == 0) {
            return Win(cells)
        }
        return this
    }
}
