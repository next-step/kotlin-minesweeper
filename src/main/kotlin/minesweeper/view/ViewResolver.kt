package minesweeper.view

import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.dto.MineBoardRequest

class ViewResolver {
    val inputMineBoardRequest: () -> MineBoardRequest = InputView::inputMineBoardRequest
    val printStartOfGame: (MineBoard) -> Unit = MineBoardView::printStartOfGame
}
