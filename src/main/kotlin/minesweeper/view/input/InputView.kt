package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.coordinate.Position

fun interface InputView {

    fun postionToOpen(board: Board): Position
}
