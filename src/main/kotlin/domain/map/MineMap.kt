package domain.map

import domain.MineSweeperInitProperty
import domain.cell.AroundMineCount
import domain.cell.Cell
import domain.collections.nestedList
import domain.mine.MineCoordinatesCreator

class MineMap private constructor(
    val cells: List<List<Cell>>
) {

    companion object {

        fun create(
            mineSweeperInitProperty: MineSweeperInitProperty,
            mineCoordinatesCreator: MineCoordinatesCreator,
        ): MineMap {
            val mineCoordinates = mineCoordinatesCreator.create(mineSweeperInitProperty)
            val cells = nestedList(
                columnSize = mineSweeperInitProperty.height.value,
                rowSize = mineSweeperInitProperty.width.value
            ) { column, row ->
                createCell(
                    column = column,
                    row = row,
                    mineCoordinates = mineCoordinates,
                )
            }
            return MineMap(cells)
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
                Cell.Mine
            } else {
                Cell.Ground(
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
}
