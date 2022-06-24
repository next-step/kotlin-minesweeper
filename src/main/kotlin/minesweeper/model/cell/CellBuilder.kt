package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

abstract class CellBuilder(val area: Area) : MineLocator {

    fun createCell(coordinate: Coordinate, firstClickCoordinate: Coordinate): Cell {
        if (isMineAt(coordinate, firstClickCoordinate)) {
            return Cell.Mine(coordinate)
        }
        return Cell.Safe(coordinate, surroundMineCountOf(coordinate, firstClickCoordinate))
    }

    private fun surroundMineCountOf(coordinate: Coordinate, firstClickCoordinate: Coordinate) = SurroundMineCount(
        area.surroundCoordinatesOf(coordinate)
            .count { this.isMineAt(it, firstClickCoordinate) }
    )

    companion object {

        operator fun invoke(area: Area, mineCellLocator: MineLocator) =
            object : CellBuilder(area) {
                override fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate) =
                    mineCellLocator.isMineAt(coordinate, firstClickCoordinate)
            }
    }
}
