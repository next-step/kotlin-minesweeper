package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object AdjacentMineCountRenderingStrategy : MineRenderingStrategy {
    override fun symbolOf(board: Board, coordinate: Coordinate): String {
        val attribute: Attribute = board.attribute(coordinate)
        if (attribute == Attribute.MINE) {
            return "*"
        }
        return board.adjacentMineCount(coordinate).toString()
    }
}
