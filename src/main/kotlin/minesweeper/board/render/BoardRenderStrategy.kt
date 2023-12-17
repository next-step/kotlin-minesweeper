package minesweeper.board.render

import minesweeper.board.BoardElement
import minesweeper.board.GameBoard

fun interface BoardRenderStrategy {

    operator fun invoke(boardElement: BoardElement, value: Char): GameBoard
}
