package controller

import domain.Board
import domain.BoardSize
import domain.Height
import domain.MineCount
import domain.Width
import presentation.InputView
import presentation.ResultView

fun main() {
    val board = Board.create(
        BoardSize(
            Width(InputView.getWidth()),
            Height(InputView.getHeight()),
        ),
        MineCount(InputView.getMineCount()),
    )
    ResultView.printBoard(board)
}
