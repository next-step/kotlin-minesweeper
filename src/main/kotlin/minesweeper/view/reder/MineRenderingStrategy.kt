package minesweeper.view.reder

import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate

interface MineRenderingStrategy {
    fun symbol(attribute: Attribute, coordinate: Coordinate): String
}
