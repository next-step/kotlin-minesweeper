package step4.domain.state

import step4.domain.CellInfo
import step4.domain.cell.Cells
import step4.domain.coordinate.Coordinate
import step4.domain.strategy.CoordinateSelectStrategy

class Running(
    var toFindCellCount: Int,
    val cells: Cells,
) : MinesweeperState {
    init {
        require(toFindCellCount > MINIMUM_TO_FIND_CELL_COUNT) { "찾아야하는 cell 갯수가 ${MINIMUM_TO_FIND_CELL_COUNT}이 될 수 없습니다." }
    }

    override fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy): MinesweeperState =
        throw IllegalStateException("지뢰를 설치할 수 있는 상태가 아닙니다.")

    override fun open(coordinate: Coordinate): MinesweeperState {
        val cellOpenResult = cells.open(coordinate)
        if (cellOpenResult == CELL_OPEN_MINE_RESULT) {
            return Lose(cells)
        }
        toFindCellCount -= cellOpenResult
        return confirmWin()
    }

    override fun isFinished(): Boolean = false

    override fun cellInfos(): List<CellInfo> = cells.cellInfos()

    private fun confirmWin(): MinesweeperState {
        if (toFindCellCount == FINISHED_GAME_TO_FIND_CELL_COUNT) {
            return Win(cells)
        }
        return this
    }

    companion object {
        private const val MINIMUM_TO_FIND_CELL_COUNT = 0
        private const val CELL_OPEN_MINE_RESULT = 0
        private const val FINISHED_GAME_TO_FIND_CELL_COUNT = 0
    }
}
