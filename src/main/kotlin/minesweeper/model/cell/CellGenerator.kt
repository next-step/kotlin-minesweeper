package minesweeper.model.cell

import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

class CellGenerator(val area: Area, private val mineLocator: MineLocator) {

    fun createCell(coordinate: Coordinate, firstClickCoordinate: Coordinate): Cell {
        if (mineLocator.isMineAt(coordinate, firstClickCoordinate)) {
            return Cell.Mine(coordinate)
        }
        return Cell.Safe(coordinate, surroundMineCountOf(coordinate, firstClickCoordinate))
    }

    private fun surroundMineCountOf(coordinate: Coordinate, firstClickCoordinate: Coordinate) = SurroundMineCount(
        area.surroundCoordinatesOf(coordinate)
            .count { mineLocator.isMineAt(it, firstClickCoordinate) }
    )
}
