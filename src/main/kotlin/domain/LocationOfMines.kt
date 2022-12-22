package domain

import domain.coord.Coord
import domain.coord.Coordinate
import domain.coord.RelativeCoordinate

class LocationOfMines(
    private val mines: List<Coordinate>
) {
    fun exist(coordinate: Coord) = mines.contains(coordinate)

    fun countByNearMines(coordinate: Coordinate): Int {
        return relativeOfCoords.count { coordinate.isPossiblePlus(it) && exist(coordinate + it) }
    }

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
