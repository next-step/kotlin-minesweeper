package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH
import domain.Cell.EmptyCell
import domain.Cell.MineCell

data class Cells(val cells: List<Cell>) {
    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()

    companion object {
        fun generateWithMines(
            mineBoardHeightSize: Int,
            mineBoardWidthSize: Int,
            mineCoordinates: Set<Coordinate>,
        ): Cells {
            val heightRange = MINIMUM_HEIGHT..mineBoardHeightSize
            val widthRange = MINIMUM_WIDTH..mineBoardWidthSize

            val coordinates =
                heightRange.flatMap { height ->
                    widthRange.map { width ->
                        Coordinate(BoardHeight(height), BoardWidth(width))
                    }
                }

            val cells = coordinates.map { coordinate -> parseCell(mineCoordinates, coordinate) }
            return Cells(cells)
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
