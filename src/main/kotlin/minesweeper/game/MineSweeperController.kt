package minesweeper.game

import minesweeper.domain.board.mineBoard
import minesweeper.domain.board.strategy.MineStrategy
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver,
    private val mineStrategy: MineStrategy
) {

    fun start() {
        val request = viewResolver.inputMineBoardRequest()
        val mineBoard = mineBoard {
            width(request.width)
            height(request.height)
            numberOfMines(request.numberOfMines)
            mineStrategy(mineStrategy.strategy())
        }
        viewResolver.printStartOfGame(mineBoard)
    }
}
