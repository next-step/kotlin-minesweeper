package minesweeper.view.reder.impl

import minesweeper.model.point.Attribute
import minesweeper.view.reder.MineRenderingStrategy

class AdjacentMineCountRenderingStrategy : MineRenderingStrategy {
    override fun symbol(attribute: Attribute): String {
        if (attribute == Attribute.MINE) {
            return "*"
        }
        if (attribute == Attribute.FLAG) {
            return "F"
        }
        TODO()
    }
}
