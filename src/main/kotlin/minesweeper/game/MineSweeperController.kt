package minesweeper.game

import minesweeper.domain.board.MineBoard
import minesweeper.view.ViewResolver

class MineSweeperController(
    private val viewResolver: ViewResolver
) {

    fun start() {
        val mineBoardRequest = viewResolver.inputMineBoardRequest()
        val mineBoard = MineBoard.of(
            width = mineBoardRequest.width,
            height = mineBoardRequest.height,
            numberOfMines = mineBoardRequest.numberOfMines
        )
        viewResolver.printStartOfGame(mineBoard)
    }
}
