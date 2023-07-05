package view.output

import domain.CellType
import domain.MineSweeperMap
import domain.NormalCellProperty

class GameMapOutputView(mineSweeperMap: MineSweeperMap) {
    init {
        mineSweeperMap.value.forEach { row ->
            val rowMessage = row.joinToString(" ") { cell ->
                when (cell.property is NormalCellProperty) {
                    true -> cell.property.aroundMineCount.value.toString()
                    false -> CellType.MINE_SYMBOL
                }
            }
            println(rowMessage)
        }
    }
}
