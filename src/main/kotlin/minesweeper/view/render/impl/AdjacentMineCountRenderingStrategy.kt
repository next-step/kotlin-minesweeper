package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.model.point.TileType
import minesweeper.view.render.MineRenderingStrategy

class AdjacentMineCountRenderingStrategy(
    private val board: Board,
) : MineRenderingStrategy {
    override fun symbol(attribute: Attribute, coordinate: Coordinate): String {
        if (attribute.tileType == TileType.MINE) {
            return "*"
        }
        if (attribute.tileType == TileType.FLAG) {
            return "F"
        }
        return board.adjacentMineCount(coordinate).toString()
    }
}
