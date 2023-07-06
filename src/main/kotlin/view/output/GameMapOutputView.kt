package view.output

import domain.MineSweeperMap

class GameMapOutputView(mineSweeperMap: MineSweeperMap) {
    init {
        mineSweeperMap.value.forEach { row ->
            val rowMessage = row.joinToString(" ") { cell -> cell.property.getSymbol() }
            println(rowMessage)
        }
    }
}
