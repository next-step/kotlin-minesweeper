package domain.mine

import domain.MineSweeperInitProperty
import domain.map.Coordinate

class MockMineCoordinatesCreator(
    private val delegator: (MineSweeperInitProperty) -> Set<Coordinate>
) : MineCoordinatesCreator {

    override fun create(mineSweeperInitProperty: MineSweeperInitProperty): Set<Coordinate> {
        return delegator(mineSweeperInitProperty)
    }
}
