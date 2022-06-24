package minesweeper.model.cell

import minesweeper.model.coordinate.Coordinate

fun interface MineLocator {
    fun isMineAt(coordinate: Coordinate, firstClickCoordinate: Coordinate): Boolean
}
