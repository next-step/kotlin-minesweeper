package controller

import model.Position
import model.Positions
import model.board.BoardFactory
import model.board.BoardSize
import view.InputView
import view.OutputView

fun main() {
    val boardSize = BoardSize(InputView.readHeight(), InputView.readWidth())
    val mineCount = InputView.readMineCount()

    val board = BoardFactory().create(boardSize, Positions.random(boardSize, mineCount))

    OutputView.printStart()
    while (true) {
        val (heightIndex, widthIndex) = InputView.readIndexes()
        val targetPosition = Position.get(heightIndex, widthIndex)

        board.uncover(targetPosition)
        OutputView.printBoard(board)
    }
}
