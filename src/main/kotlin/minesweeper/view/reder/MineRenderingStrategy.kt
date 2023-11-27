package minesweeper.view.reder

import minesweeper.model.point.Attribute

interface MineRenderingStrategy {
    fun symbol(attribute: Attribute): String
}
