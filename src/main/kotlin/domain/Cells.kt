package domain

import domain.Cell.EmptyCell
import domain.Cell.MineCell

data class Cells(val cells: List<Cell>) {
    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()

    companion object {
        fun generateWithMines(
            heightRange: IntRange,
            widthRange: IntRange,
            mineCoordinates: Set<Coordinate>,
        ): Cells {
            val cells =
                heightRange.flatMap { height ->
                    widthRange.map { width ->
                        val coordinate = Coordinate(BoardHeight(height), BoardWidth(width))
                        if (coordinate in mineCoordinates) {
                            MineCell(coordinate)
                        } else {
                            EmptyCell(coordinate)
                        }
                    }
                }
            return Cells(cells)
        }
    }
}
