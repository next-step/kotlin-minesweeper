package domain.map

import domain.MineSweeperInitProperty
import domain.cell.Cell
import domain.collections.nestedList

class MineMap private constructor(
    val cells: List<List<Cell>>
) {

    companion object {

        fun create(
            mineSweeperInitProperty: MineSweeperInitProperty,
        ): MineMap {
            val cells = nestedList(
                columnSize = mineSweeperInitProperty.height.value,
                rowSize = mineSweeperInitProperty.width.value
            ) { _, _ ->
                Cell
            }
            return MineMap(cells)
        }
    }
}
