package minesweeper.model.board

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate

class Mines(
    private val deployedCoordinate: Map<Coordinate, Attribute>,
) {

    val count: Int
        get() = deployedCoordinate.values.count { it == Attribute.MINE }

    val coordinates: Set<Coordinate>
        get() = deployedCoordinate.keys

    fun isDeployedCoordinate(coordinate: Coordinate): Boolean {
        return deployedCoordinate.containsKey(coordinate)
    }

}

fun Map<Coordinate, Attribute>.toMines(): Mines {
    return Mines(this)
}
