package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell.EmptyCell
import domain.Cell.MineCell
import domain.strategy.MineCellGenerator

class Cells private constructor(val cells: List<Cell>) {
    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()

    companion object {
        fun generateWithMines(
            mineGameMetric: MineGameMetric,
            mineCellGenerator: MineCellGenerator,
        ): Cells {
            val emptyCellCoordinates = generateEmptyCells(mineGameMetric.boardHeightSize, mineGameMetric.boardWidthSize)
            val mineCellCoordinates =
                generateMineCells(
                    mineCellGenerator,
                    Coordinate(BoardHeight(mineGameMetric.boardHeightSize), BoardWidth(mineGameMetric.boardWidthSize)),
                    mineGameMetric.mineCount,
                )

            val cells = emptyCellCoordinates.map { coordinate -> parseCell(mineCellCoordinates, coordinate) }
            return Cells(cells)
        }

        private fun generateEmptyCells(
            mineBoardHeightSize: Int,
            mineBoardWidthSize: Int,
        ): List<Coordinate> {
            val heightRange = MINIMUM_HEIGHT..mineBoardHeightSize
            val widthRange = MINIMUM_WIDTH..mineBoardWidthSize

            return heightRange.flatMap { height ->
                widthRange.map { width ->
                    Coordinate(BoardHeight(height), BoardWidth(width))
                }
            }
        }

        private fun generateMineCells(
            mineCellGenerator: MineCellGenerator,
            coordinate: Coordinate,
            mineCount: Int,
        ): Set<Coordinate> {
            return mineCellGenerator.execute(coordinate, mineCount).map { it.coordinate }.toSet()
        }

        private fun parseCell(
            mineCoordinates: Set<Coordinate>,
            coordinate: Coordinate,
        ): Cell {
            if (coordinate in mineCoordinates) {
                return MineCell(coordinate)
            }
            return EmptyCell(coordinate)
        }
    }
}
