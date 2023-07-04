package minesweeper.domain

import minesweeper.domain.CoordinateFinder.Companion.nearCoordinates
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellType.Companion.toCellType
import minesweeper.domain.cell.Coordinate.Companion.isContains
import minesweeper.domain.strategy.MinePlacementStrategy
import minesweeper.domain.strategy.RandomMinePlacementStrategy

class MineBoard(
    private val height: Int,
    private val width: Int,
    val cells: List<Cell>,
    private val minePlacementStrategy: MinePlacementStrategy = RandomMinePlacementStrategy(),
) {
    init {
        require(height >= MINIMUM_HEIGHT) { "지뢰찾기맵 높이는 ${MINIMUM_HEIGHT}이상이어야 합니다." }
        require(width >= MINIMUM_WIDTH) { "지뢰찾기맵 너비는 ${MINIMUM_WIDTH}이상이어야 합니다." }
    }

    fun placeMine(mineCount: Int) {
        validateMineCount(mineCount)
        repeat(mineCount) { minePlacementStrategy.findPlantTargetCell(cells).changeToMine() }
        val mines = cells.filter { it.isMine() }
        cells.filterNot { it.isMine() }
            .forEach { calculate(it, mines) }
    }

    fun currentBoard(): CellInfos =
        CellInfos(height = height, width = width, values = cells.map { CellInfo.from(it) })

    private fun validateMineCount(mineCount: Int) {
        require(mineCount >= MINIMUM_MINE_COUNT) { "지뢰 갯수는 ${MINIMUM_MINE_COUNT}이상이어야 합니다." }
        require(mineCount < cells.size) { "지뢰 갯수는 현재 cell크기 ${cells.size}보다 작은 값을 입력하여야 합니다." }
        check(cells.none { it.isMine() }) { "이미 지뢰가 배치되어 있습니다." }
    }

    private fun calculate(cell: Cell, mines: List<Cell>) {
        val nearCoordinates = nearCoordinates(cell)
        val cellType = mines.count { nearCoordinates.isContains(it) }.toCellType()
        cell.changeToCellType(cellType)
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 1

        private const val MINIMUM_HEIGHT = 1
        private const val MINIMUM_WIDTH = 1

        fun generateNewMineBoard(height: Int, width: Int): MineBoard {
            return MineBoard(height, width, List(height * width) { index -> parseToCell(index, width) })
        }

        private fun parseToCell(index: Int, width: Int): Cell {
            val row = index / width
            val column = index % width
            return Cell(row, column)
        }
    }
}
