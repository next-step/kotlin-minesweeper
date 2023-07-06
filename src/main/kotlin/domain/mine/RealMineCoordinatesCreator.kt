package domain.mine

import domain.MineSweeperInitProperty
import domain.map.Coordinate
import utils.nestedList

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
