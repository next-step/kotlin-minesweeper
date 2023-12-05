package minesweeper.model.board

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate

class Mines(
    private val deployedCoordinate: Map<Coordinate, Attribute>,
) {
    fun isDeployedCoordinate(coordinate: Coordinate): Boolean {
        return deployedCoordinate.containsKey(coordinate)
    }

    val count: Int
        get() = deployedCoordinate.values.count { it == Attribute.MINE }

    val coordinates: Set<Coordinate>
        get() = deployedCoordinate.keys
}

fun Map<Coordinate, Attribute>.toMines(): Mines {
    return Mines(this)
}
