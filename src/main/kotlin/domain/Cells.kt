package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell.EmptyCell
import domain.Cell.MineCell
import domain.strategy.MineCellGenerator

@JvmInline
value class Cells(val cells: List<Cell>) {
    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()

    fun getCoordinateIs(coordinate: Coordinate): Cell {
        return cells.firstOrNull { it.coordinate == coordinate }
            ?: throw NoSuchElementException("Coordinate $coordinate not found")
    }

    companion object {
        fun generateWithMines(
            mineGameMetric: MineGameMetric,
            mineCellGenerator: MineCellGenerator,
        ): Cells {
            val emptyCellCoordinates = generateEmptyCells(mineGameMetric.boardHeightSize, mineGameMetric.boardWidthSize)
            val mineCellCoordinates =
                generateMineCells(
                    mineCellGenerator,
                    mineGameMetric,
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
                    Coordinate(Row(height), Col(width))
                }
            }
        }

        private fun generateMineCells(
            mineCellGenerator: MineCellGenerator,
            mineGameMetric: MineGameMetric,
        ): Set<Coordinate> {
            return mineCellGenerator.execute(mineGameMetric).map { it.coordinate }.toSet()
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
