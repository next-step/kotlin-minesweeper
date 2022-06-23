package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class CellBuilder(private val area: Area, private val isMineCell: (Coordinate) -> Boolean) {

    fun createCell(coordinate: Coordinate): Cell {
        if (isMineCell(coordinate)) {
            return Cell.Mine(coordinate)
        }

        return Cell.Safe(coordinate, surroundMineCountOf(coordinate))
    }

    private fun surroundMineCountOf(coordinate: Coordinate) = SurroundMineCount(
        area.surroundCoordinatesOf(coordinate)
            .count(isMineCell)
    )
}
