package domain.block

import domain.LocationOfMines
import domain.coord.AbstractCoordinate

object BlockFactory {

    fun create(coordinate: AbstractCoordinate, mines: LocationOfMines): Block {
        return if (mines.exist(coordinate)) {
            Mine()
        } else {
            Land(mines.countByNearMines(coordinate))
        }
    }
}
