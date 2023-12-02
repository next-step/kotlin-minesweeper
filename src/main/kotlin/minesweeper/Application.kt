package minesweeper

import minesweeper.domain.*
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.receiveHeight()
    val width = InputView.receiveWidth()
    val mineCount = InputView.receiveMineCount()
    val board = Board(BoardInformation(Height(height), Width(width), MineCount(mineCount)))
    OutputView.printBoard(board)
}
