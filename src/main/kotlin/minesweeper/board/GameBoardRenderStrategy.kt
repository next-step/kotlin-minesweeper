package minesweeper.board

import minesweeper.board.BoardDimensions
import minesweeper.board.RenderedGameBoard

fun interface GameBoardRenderStrategy {

     operator fun invoke(boardDimensions: BoardDimensions, default: Char): RenderedGameBoard

     companion object {
         const val MINE = '*'
     }
}
