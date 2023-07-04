package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cells
import minesweeper.domain.strategy.MinePlacementStrategy
import minesweeper.domain.strategy.RandomMinePlacementStrategy

class MineBoard(
    private val height: Int,
    private val width: Int,
    val cells: Cells,
    private val minePlacementStrategy: MinePlacementStrategy = RandomMinePlacementStrategy(),
) {
    init {
        require(height >= MINIMUM_HEIGHT) { "지뢰찾기맵 높이는 ${MINIMUM_HEIGHT}이상이어야 합니다." }
        require(width >= MINIMUM_WIDTH) { "지뢰찾기맵 너비는 ${MINIMUM_WIDTH}이상이어야 합니다." }
    }

    fun placeMine(mineCount: Int) {
        cells.placeMine(mineCount, minePlacementStrategy)
    }

    fun currentBoard(): CellInfos = CellInfos(height = height, width = width, values = cells.cellInfos())

    companion object {
        private const val MINIMUM_HEIGHT = 1
        private const val MINIMUM_WIDTH = 1

        fun generateNewMineBoard(height: Int, width: Int): MineBoard {
            return MineBoard(height, width, Cells(List(height * width) { index -> parseToCell(index, width) }))
        }

        private fun parseToCell(index: Int, width: Int): Cell {
            val row = index / width
            val column = index % width
            return Cell(row, column)
        }
    }
}
