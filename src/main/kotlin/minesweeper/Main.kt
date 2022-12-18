package minesweeper

import minesweeper.domain.ColumnCount
import minesweeper.domain.MineCount
import minesweeper.domain.MineBoard
import minesweeper.domain.RowCount
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()

    val rowCount: Int = inputView.inputRowCount()
    val columnCount: Int = inputView.inputColumnCount()
    val mineCount: Int = inputView.inputMineCount()

    val board = MineBoard(RowCount(rowCount), ColumnCount(columnCount), MineCount(mineCount))

    val resultView = ResultView()
    resultView.showBoard(board)
}
