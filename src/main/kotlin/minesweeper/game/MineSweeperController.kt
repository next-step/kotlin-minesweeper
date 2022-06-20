package minesweeper.game

import minesweeper.domain.board.mineBoard
import minesweeper.domain.board.random.RandomMineStrategy
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver,
    private val randomMineStrategy: RandomMineStrategy
) {

    fun start() {
        val request = viewResolver.inputMineBoardRequest()
        val mineBoard = mineBoard {
            width(request.width)
            height(request.height)
            numberOfMines(request.numberOfMines)
            mineStrategy(randomMineStrategy.strategy())
        }
        viewResolver.printStartOfGame(mineBoard)
    }
}
