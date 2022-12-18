package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.MineBoard
import minesweeper.domain.Row
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()

    val columnCount: Int = inputView.inputColumnCount()
    val rowCount: Int = inputView.inputRowCount()
    val mineCount: Int = inputView.inputMineCount()

    val board = MineBoard(Row(rowCount), Column(columnCount), mineCount)

    val resultView = ResultView()
    resultView.showBoard(board)
}
