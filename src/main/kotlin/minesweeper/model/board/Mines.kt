package minesweeper.model.board

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Delta

class Mines(
    private val deployedCoordinate: Map<Coordinate, Attribute>,
    private val limit: BoardLimit,
) {

    val count: Int
        get() = deployedCoordinate.values.count { it == Attribute.MINE }

    val coordinates: Set<Coordinate>
        get() = deployedCoordinate.keys

    fun isDeployedCoordinate(coordinate: Coordinate): Boolean {
        return deployedCoordinate.containsKey(coordinate)
    }

//    fun isGround(coordinate: Coordinate): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    fun isAdjacentMineCountZero(coordinate: Coordinate): Boolean {
//        TODO("Not yet implemented")
//    }

    fun attribute(coordinate: Coordinate): Attribute {
        return when (this.isDeployedCoordinate(coordinate)) {
            true -> Attribute.MINE
            false -> Attribute.GROUND
        }
    }

    fun isGround(coordinate: Coordinate): Boolean =
        this.attribute(coordinate) == Attribute.GROUND

    fun isAdjacentMineCountZero(coordinate: Coordinate): Boolean =
        this.adjacentMineCount(coordinate) == 0

    fun adjacentMineCount(coordinate: Coordinate): Int {
        return Delta.deltas.asSequence()
            .filter { delta -> inRange(coordinate, delta) }
            .map { this.attribute(coordinate.moveTo(it)) }
            .count { it.isMine() }
    }

    private fun inRange(coordinate: Coordinate, delta: Delta): Boolean {
        return coordinate.movePossible(
            delta = delta,
            limit = limit
        )
    }
}

fun Map<Coordinate, Attribute>.toMines(limit: BoardLimit): Mines {
    return Mines(this, limit)
}
