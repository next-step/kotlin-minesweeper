package minesweeper.view.reder.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Attribute
import minesweeper.model.point.Coordinate
import minesweeper.view.reder.MineRenderingStrategy

class AdjacentMineCountRenderingStrategy(
    private val board: Board,
    private val coordinate: Coordinate
) : MineRenderingStrategy {
    override fun symbol(attribute: Attribute): String {
        if (attribute == Attribute.MINE) {
            return "*"
        }
        if (attribute == Attribute.FLAG) {
            return "F"
        }
        return board.adjacentMineCount(coordinate).toString()
    }
}
