package minesweeper

import minesweeper.domain.ColumnCount
import minesweeper.domain.MineBoard
import minesweeper.domain.MineCount
import minesweeper.domain.RowCount
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()

    val rowCount: RowCount = inputView.inputRowCount()
    val columnCount: ColumnCount = inputView.inputColumnCount()
    val mineCount: MineCount = inputView.inputMineCount()

    val board = MineBoard(rowCount, columnCount, mineCount)

    val resultView = ResultView()
    resultView.showBoard(board)
}
