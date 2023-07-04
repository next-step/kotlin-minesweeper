package domain.map

import domain.MineSweeperInitProperty
import domain.cell.AroundMineCount
import domain.cell.Cell
import domain.mine.MineCoordinatesCreator
import utils.nestedList

class RealMineMapFactory(
    private val mineCoordinatesCreator: MineCoordinatesCreator,
) : MineMapFactory {

    override fun create(mineSweeperInitProperty: MineSweeperInitProperty): MineMap {
        val mineCoordinates = mineCoordinatesCreator.create(mineSweeperInitProperty)
        val cells = createCells(mineCoordinates, mineSweeperInitProperty)
            .map { it.toMutableList() }
            .toMutableList()
        return MineMap(cells)
    }

    private fun createCells(
        mineCoordinates: Set<Coordinate>,
        mineSweeperInitProperty: MineSweeperInitProperty,
    ): List<List<Cell>> {
        return nestedList(
            columnSize = mineSweeperInitProperty.height.value,
            rowSize = mineSweeperInitProperty.width.value
        ) { column, row ->
            createCell(
                column = column,
                row = row,
                mineCoordinates = mineCoordinates,
            )
        }
    }

    private fun createCell(
        column: Int,
        row: Int,
        mineCoordinates: Set<Coordinate>,
    ): Cell {
        val coordinate = Coordinate(
            x = row,
            y = column,
        )
        return if (mineCoordinates.contains(coordinate)) {
            Cell.hideMine()
        } else {
            Cell.hideGround(
                aroundMineCount = coordinate.calculateAroundMineCount(mineCoordinates),
            )
        }
    }

    private fun Coordinate.calculateAroundMineCount(
        mineCoordinates: Set<Coordinate>,
    ): AroundMineCount {
        val count = aroundCoordinates().count { mineCoordinates.contains(it) }
        return AroundMineCount(count)
    }
}
