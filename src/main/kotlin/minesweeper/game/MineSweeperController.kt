package minesweeper.game

import minesweeper.domain.board.MineMaker
import minesweeper.domain.board.mineBoard
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver,
    private val mineMaker: MineMaker
) {

    fun start() {
        val request = viewResolver.inputMineBoardRequest()
        val mineBoard = mineBoard {
            width(request.width)
            height(request.height)
            numberOfMines(request.numberOfMines)
            mineStrategy(mineMaker)
        }
        viewResolver.printStartOfGame(mineBoard)
    }
}
