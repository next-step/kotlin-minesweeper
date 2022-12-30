package minesweeper

import minesweeper.domain.MineBoard
import minesweeper.domain.MineMap
import minesweeper.domain.Position
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineCount = InputView.askMineCount(height * width)

    val mineBoard = MineBoard(MineMap.createMineMap(height, width, mineCount))

    OutputView.printStart()
    do {
        val (row, column) = InputView.askOpenPosition(height, width)
        val cellOpenResult = mineBoard.openCell(Position(row, column))
        OutputView.printMineBoard(mineBoard.snapshot())
    } while (!cellOpenResult.lose() && !mineBoard.win())

    OutputView.printResult(mineBoard.win())
}
