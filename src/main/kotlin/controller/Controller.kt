package controller

import domain.Board
import domain.BoardSize
import domain.Height
import domain.MineCount
import domain.RandomNonNegativeNumberGenerator
import domain.Width
import presentation.InputView
import presentation.ResultView

fun main() {
    val boardSize = BoardSize(
        Width(InputView.getWidth()),
        Height(InputView.getHeight()),
    )
    val mineCount = MineCount(InputView.getMineCount())

    val board = Board.create(
        boardSize,
        RandomNonNegativeNumberGenerator(
            to = boardSize.numberOfSpaces,
            count = mineCount.value,
        )
    )
    ResultView.printBoard(board)
}
