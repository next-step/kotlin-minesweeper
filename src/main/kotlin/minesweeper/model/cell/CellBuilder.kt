package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

abstract class CellBuilder(val area: Area) {

    fun createCell(coordinate: Coordinate, firstClickCoordinate: Coordinate): Cell {
        if (isMineCell(coordinate, firstClickCoordinate)) {
            return Cell.Mine(coordinate)
        }
        return Cell.Safe(coordinate, surroundMineCountOf(coordinate, firstClickCoordinate))
    }

    private fun surroundMineCountOf(coordinate: Coordinate, firstClickCoordinate: Coordinate) = SurroundMineCount(
        area.surroundCoordinatesOf(coordinate)
            .count { this.isMineCell(it, firstClickCoordinate) }
    )

    abstract fun isMineCell(coordinate: Coordinate, firstClickCoordinate: Coordinate): Boolean
}

fun CellBuilder(area: Area, isMineCellBlock: (Coordinate, Coordinate) -> Boolean) = object : CellBuilder(area) {
    override fun isMineCell(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
        isMineCellBlock(coordinate, firstClickCoordinate)
}
