package minesweeper.view.render.impl

import minesweeper.model.board.Board
import minesweeper.model.point.Coordinate
import minesweeper.view.render.MineRenderingStrategy

object ExploringDefaultClosedAreaRenderingStrategy : MineRenderingStrategy {
    override fun symbolOf(board: Board, coordinate: Coordinate): String {
        if (board.isCovered(coordinate)) {
            return "C"
        }
        return AdjacentMineCountRenderingStrategy.symbolOf(board, coordinate)
    }
}
