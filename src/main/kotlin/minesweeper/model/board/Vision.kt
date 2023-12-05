package minesweeper.model.board

import minesweeper.model.point.Coordinate

class Vision(coordinates: Set<Coordinate>) {

    private val coveredCoordinates: MutableSet<Coordinate>

    val coveredCount: Int
        get() = coveredCoordinates.size

    init {
        this.coveredCoordinates = coordinates.toMutableSet()
    }

    fun exposeAll(coordinates: Set<Coordinate>) {
        this.coveredCoordinates.removeAll(coordinates)
    }

    fun isCovered(coordinate: Coordinate): Boolean {
        return coveredCoordinates.contains(coordinate)
    }
}

fun Set<Coordinate>.toVision(): Vision {
    return Vision(this)
}
