package domain

import domain.coord.AbstractCoordinate
import domain.coord.Coordinate
import domain.coord.RelativeCoordinate.Companion.relativeOfCoords

class LocationOfMines(
    private val mines: List<AbstractCoordinate>
) {
    fun countByNearMines(coordinate: AbstractCoordinate): Int {
        return relativeOfCoords.count { coordinate.isPossiblePlus(it) && exist(coordinate + it) }
    }

    fun exist(coordinate: Coordinate) = mines.contains(coordinate)
}
