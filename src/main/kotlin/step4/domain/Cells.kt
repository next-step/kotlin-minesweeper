package step4.domain

import step4.domain.CellType.Companion.toCellType
import step4.domain.strategy.CoordinateSelectStrategy

@JvmInline
value class Cells(
    val values: Map<Coordinate, Cell>,
) {
    fun installMine(mineCount: Int, coordinateSelectStrategy: CoordinateSelectStrategy) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}개 이상이어야 합니다." }
        require(mineCount < values.size) { "보유한 cell보다 많은 지뢰를 설치할 수 없습니다." }
        repeat(mineCount) { coordinateSelectStrategy.select(values).toMine() }

        val mineCoordinates = findMineCoordinates()
        values.filterNot { it.value.isMine() }
            .forEach { parseCellType(it.key, it.value, mineCoordinates) }
    }

    fun open(coordinate: Coordinate): Int {
        val cell = values[coordinate] ?: throw IllegalArgumentException("존재하지 않는 좌표는 입력될 수 없습니다.")
        return open(cell)
    }

    private fun findMineCoordinates(): Set<Coordinate> = values.filter { it.value.isMine() }
        .map { it.key }
        .toSet()

    private fun parseCellType(
        coordinate: Coordinate,
        cell: Cell,
        mineCoordinates: Set<Coordinate>,
    ) {
        val cellType = calculateCellType(coordinate, mineCoordinates)
        cell.changeCellType(cellType)
    }

    private fun calculateCellType(
        coordinate: Coordinate,
        mineCoordinates: Set<Coordinate>,
    ): CellType {
        val nearCoordinates = CoordinateFinder.nearCoordinates(coordinate)
        return nearCoordinates.count { mineCoordinates.contains(it) }
            .toCellType()
    }

    private fun open(cell: Cell): Int {
        cell.open()
        if (cell.isMine()) {
            return 0
        }
        return 1
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1
    }
}
