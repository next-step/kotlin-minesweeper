package minesweeper

import minesweeper.domain.ColumnCount
import minesweeper.domain.MineBoard
import minesweeper.domain.MineCount
import minesweeper.domain.RowCount
import minesweeper.domain.times
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()

    val rowCount: RowCount = inputView.inputRowCount()
    val columnCount: ColumnCount = inputView.inputColumnCount()
    val mineCount: MineCount = inputView.inputMineCount(boardSize = rowCount * columnCount)

    val board = MineBoard {
        rows(rowCount)
        columns(columnCount)
        mines(mineCount)
    }

    val resultView = ResultView()
    resultView.showBoard(board)
}
