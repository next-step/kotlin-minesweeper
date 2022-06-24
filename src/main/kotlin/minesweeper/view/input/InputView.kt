package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.coordinate.Coordinate

fun interface InputView {
    fun coordinateToOpen(board: Board): Coordinate
}
