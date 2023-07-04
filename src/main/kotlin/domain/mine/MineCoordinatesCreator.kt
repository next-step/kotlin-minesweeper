package domain.mine

import domain.MineSweeperInitProperty
import domain.map.Coordinate

fun interface MineCoordinatesCreator {

    fun create(
        mineSweeperInitProperty: MineSweeperInitProperty,
    ): Set<Coordinate>
}
