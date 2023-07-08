package minesweeper.domain.cell

import minesweeper.domain.CellInfo
import minesweeper.domain.CoordinateFinder
import minesweeper.domain.cell.CellType.Companion.toCellType
import minesweeper.domain.cell.CellType.ZERO
import minesweeper.domain.strategy.MinePlacementStrategy

@JvmInline
value class Cells(
    val values: Map<Coordinate, Cell>,
) {
    constructor(cells: List<Cell>) : this(cells.associateBy { it.coordinate })

    constructor(vararg cells: Cell) : this(cells.associateBy { it.coordinate })

    fun placeMine(mineCount: Int, minePlacementStrategy: MinePlacementStrategy) {
        validateMineCount(mineCount)
        repeat(mineCount) { minePlacementStrategy.findPlantTargetCell(values).changeToMine() }
        val mineCoordinates = values.filter { it.value.isMine() }
            .map { it.key }
            .toSet()
        values.filterNot { it.value.isMine() }
            .forEach { calculate(it.value, mineCoordinates) }
    }

    fun open(coordinate: Coordinate) {
        val cell = values[coordinate]
        require(cell != null) { "존재하지 않는 좌표는 입력될 수 없습니다." }
        cell.changeToDisplay()
        if (cell.cellType != ZERO) {
            cell.changeToDisplay()
            return
        }
        openAroundCell(cell)
    }

    private fun openAroundCell(cell: Cell) {
        val nearCells = CoordinateFinder.nearCoordinates(cell)
            .mapNotNull { values[it] }
            .filterNot { it.isDisplay }
        nearCells.forEach { it.changeToDisplay() }
        nearCells.filter { it.cellType == ZERO }
            .forEach { openAroundCell(it) }
    }

    fun cellInfos(): List<CellInfo> = values.map { CellInfo.from(it.value) }

    private fun validateMineCount(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}이상이어야 합니다." }
        require(mineCount < values.size) { "지뢰 갯수는 현재 cell크기 ${values.size}보다 작은 값을 입력하여야 합니다." }
        check(values.none { it.value.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
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
