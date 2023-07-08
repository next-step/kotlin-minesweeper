package minesweeper.domain.cell

import minesweeper.domain.CellInfo
import minesweeper.domain.CoordinateFinder
import minesweeper.domain.cell.CellType.Companion.toCellType
import minesweeper.domain.strategy.MinePlacementStrategy

@JvmInline
value class Cells(
    val values: List<Cell>,
) {
    fun placeMine(mineCount: Int, minePlacementStrategy: MinePlacementStrategy) {
        validateMineCount(mineCount)
        repeat(mineCount) { minePlacementStrategy.findPlantTargetCell(values).changeToMine() }
        val mineCoordinates = values.filter { it.isMine() }
            .map { it.coordinate }
            .toSet()
        values.filterNot { it.isMine() }
            .forEach { calculate(it, mineCoordinates) }
    }

    fun cellInfos(): List<CellInfo> = values.map { CellInfo.from(it) }

    private fun validateMineCount(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}이상이어야 합니다." }
        require(mineCount < values.size) { "지뢰 갯수는 현재 cell크기 ${values.size}보다 작은 값을 입력하여야 합니다." }
        check(values.none { it.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
    }

    private fun calculate(cell: Cell, mineCoordinates: Set<Coordinate>) {
        val nearCoordinates = CoordinateFinder.nearCoordinates(cell)
        val cellType = nearCoordinates.count { mineCoordinates.contains(it) }
            .toCellType()
        cell.changeToCellType(cellType)
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1
    }
}
