package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.receiveHeight()
    val width = InputView.receiveWidth()
    val mineCount = InputView.receiveMineCount()
    val board = Board(Height(height), Width(width), MineCount(mineCount))
    OutputView.printBoard(board)
}
