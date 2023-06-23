package minesweeper

import minesweeper.convert.convertToView
import minesweeper.domain.BoardSize
import minesweeper.domain.MinesweeperBoardGenerator
import minesweeper.domain.PositiveNumber
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val minesweeperBoard = MinesweeperBoardGenerator.generate(
        width = BoardSize(value = InputView.readWidth()),
        height = BoardSize(value = InputView.readHeight()),
        mineCount = PositiveNumber(value = InputView.readMineCount()),
    )

    ResultView.printCurrentMinesweeperBoard(
        boardView = minesweeperBoard.convertToView(),
    )
}
