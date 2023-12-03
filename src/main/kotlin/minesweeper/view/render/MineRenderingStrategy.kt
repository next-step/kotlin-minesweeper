package minesweeper.view.render

import minesweeper.model.board.Board
import minesweeper.model.point.Coordinate

interface MineRenderingStrategy {
    fun symbolOf(board: Board, coordinate: Coordinate): String
}
