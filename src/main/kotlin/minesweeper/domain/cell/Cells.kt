package minesweeper.domain.cell

import minesweeper.domain.CellInfo
import minesweeper.domain.CoordinateFinder
import minesweeper.domain.cell.CellType.Companion.toCellType
import minesweeper.domain.cell.Coordinate.Companion.isContains
import minesweeper.domain.strategy.MinePlacementStrategy

@JvmInline
value class Cells(
    val values: List<Cell>,
) {
    fun placeMine(mineCount: Int, minePlacementStrategy: MinePlacementStrategy) {
        validateMineCount(mineCount)
        repeat(mineCount) { minePlacementStrategy.findPlantTargetCell(values).changeToMine() }
        val mines = values.filter { it.isMine() }
        values.filterNot { it.isMine() }
            .forEach { calculate(it, mines) }
    }

    fun cellInfos(): List<CellInfo> = values.map { CellInfo.from(it) }

    private fun validateMineCount(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}이상이어야 합니다." }
        require(mineCount < values.size) { "지뢰 갯수는 현재 cell크기 ${values.size}보다 작은 값을 입력하여야 합니다." }
        check(values.none { it.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
    }

    private fun calculate(cell: Cell, mines: List<Cell>) {
        val nearCoordinates = CoordinateFinder.nearCoordinates(cell)
        val cellType = mines.count { nearCoordinates.isContains(it) }.toCellType()
        cell.changeToCellType(cellType)
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1
    }
}
