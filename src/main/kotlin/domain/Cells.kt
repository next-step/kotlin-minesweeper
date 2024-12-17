package domain

import domain.Cell.EmptyCell
import domain.Cell.MineCell

class Cells(private val cells: List<Cell>) {
    fun placeMines(mineCells: Cells): Cells {
        val mineCoordinates = mineCells.cells.map { it.coordinate }.toSet()

        val updatedCells =
            cells.map { cell ->
                if (cell.coordinate in mineCoordinates) {
                    MineCell(cell.coordinate)
                } else {
                    cell
                }
            }

        return Cells(updatedCells)
    }

    fun mineCells(): List<MineCell> = cells.filterIsInstance<MineCell>()

    fun emptyCells(): List<EmptyCell> = cells.filterIsInstance<EmptyCell>()

    companion object {
        fun generate(
            heightRange: IntRange,
            widthRange: IntRange,
        ): Cells {
            val cells =
                heightRange.flatMap { height ->
                    widthRange.map { width ->
                        EmptyCell(
                            Coordinate(BoardHeight(height), BoardWidth(width)),
                        )
                    }
                }
            return Cells(cells)
        }
    }
}
