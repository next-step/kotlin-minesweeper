package minesweeper.controller

import minesweeper.domain.AskType
import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val board = Board(
        InputView.askBoardInfo(AskType.HEIGHT),
        InputView.askBoardInfo(AskType.WIDTH),
        InputView.askBoardInfo(AskType.MINE_COUNT)
    )

    ResultView.showBoard(board)
}
