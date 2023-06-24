package minesweeper

import minesweeper.convert.convertToView
import minesweeper.domain.BoardSize
import minesweeper.domain.MinesweeperBoardGenerator
import minesweeper.domain.PositiveNumber
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val boardSize = BoardSize(
        width = InputView.readWidth(),
        height = InputView.readHeight(),
    )

    val mineCount = PositiveNumber(value = InputView.readMineCount())

    val minesweeperBoard = MinesweeperBoardGenerator.generate(
        boardSize = boardSize,
        mineCount = mineCount,
    )

    ResultView.printCurrentMinesweeperBoard(
        boardView = minesweeperBoard.convertToView(),
    )
}
