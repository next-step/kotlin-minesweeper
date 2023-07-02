package domain.mine

import domain.MineSweeperInitProperty
import domain.map.Coordinate

interface MineCoordinatesCreator {

    fun create(
        mineSweeperInitProperty: MineSweeperInitProperty,
    ): Set<Coordinate>
}
