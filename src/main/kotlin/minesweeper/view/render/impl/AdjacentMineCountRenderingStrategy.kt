package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.TileType
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

class AdjacentMineCountRenderingStrategy(
    private val board: Board,
) : MineRenderingStrategy {
    override fun symbol(tileType: TileType, coordinate: Coordinate): String {
        if (tileType == TileType.MINE) {
            return "*"
        }
        if (tileType == TileType.FLAG) {
            return "F"
        }
        return board.adjacentMineCount(coordinate).toString()
    }
}
