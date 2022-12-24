package domain

import domain.coord.AbstractCoordinate
import domain.coord.Coordinate
import domain.coord.RelativeCoordinate

class LocationOfMines(
    private val mines: List<AbstractCoordinate>
) {
    fun countByNearMines(coordinate: AbstractCoordinate): Int {
        return relativeOfCoords.count { coordinate.isPossiblePlus(it) && exist(coordinate + it) }
    }

    fun exist(coordinate: Coordinate) = mines.contains(coordinate)

    companion object {
        private val relativeOfCoords: List<RelativeCoordinate> = listOf(
            RelativeCoordinate(-1, -1),
            RelativeCoordinate(-1, 0),
            RelativeCoordinate(-1, 1),
            RelativeCoordinate(0, -1),
            RelativeCoordinate(0, 1),
            RelativeCoordinate(1, -1),
            RelativeCoordinate(1, 0),
            RelativeCoordinate(1, 1)
        )
    }
}
