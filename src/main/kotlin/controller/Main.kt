package controller

import model.BoardFactory
import model.Position
import model.RandomMineStrategy
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val boardFactory = BoardFactory(RandomMineStrategy())
    val board = boardFactory.build(height, width, InputView.readMineCount())

    OutputView.printStart()
    while (!board.over()) {
        val position = Position(InputView.readPosition(), maxHeight = height - 1, maxWidth = width - 1)
        board.open(position)
        OutputView.printBoard(board.asDTO())
    }
    if (board.winning()) {
        OutputView.printWin()
    }
    if (board.lost()) {
        OutputView.printLose()
    }
}