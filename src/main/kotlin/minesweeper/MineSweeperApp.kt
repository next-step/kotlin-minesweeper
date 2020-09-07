package minesweeper

import minesweeper.domain.GridBoard
import minesweeper.domain.squares.RandomSquaresShufflingStrategy
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height: Int = InputView.readHeight()
    val width: Int = InputView.readWidth()
    val mineCount: Int = InputView.readMineCount(height, width)

    val board: GridBoard = GridBoard(height, width)
        .mineLaid(mineCount, RandomSquaresShufflingStrategy)
        .mineCounted()

    ResultView.showBoard(board)
}
