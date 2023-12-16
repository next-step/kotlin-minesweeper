package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object AdjacentMineCountRenderingStrategy : MineRenderingStrategy {
    override fun symbolOf(board: Board, coordinate: Coordinate): String {
        return when (board.mines.attribute(coordinate)) {
            Attribute.MINE -> "*"
            Attribute.GROUND -> board.mines.adjacentMineCount(coordinate).toString()
        }
    }
}
