package domain.mine

import domain.MineSweeperInitProperty
import utils.nestedList
import domain.map.Coordinate

class RealMineCoordinatesCreator : MineCoordinatesCreator {

    override fun create(
        mineSweeperInitProperty: MineSweeperInitProperty,
    ): Set<Coordinate> {
        return nestedList(
            columnSize = mineSweeperInitProperty.height.value,
            rowSize = mineSweeperInitProperty.width.value
        ) { column, row ->
            Coordinate(
                x = row,
                y = column,
            )
        }.flatten()
            .shuffled()
            .take(mineSweeperInitProperty.mineCount.value)
            .toSet()
    }
}
