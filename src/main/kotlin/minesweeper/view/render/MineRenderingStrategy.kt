package minesweeper.view.render

import minesweeper.model.point.TileType
import minesweeper.model.point.Coordinate

interface MineRenderingStrategy {
    fun symbol(tileType: TileType, coordinate: Coordinate): String
}
