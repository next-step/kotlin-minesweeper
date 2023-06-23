package minesweeper

import minesweeper.convert.convertToView
import minesweeper.domain.BoardSize
import minesweeper.domain.MinesweeperBoardGenerator
import minesweeper.domain.PositiveNumber
import minesweeper.view.ResultView

fun main() {
    val minesweeperBoard = MinesweeperBoardGenerator.generate(
        width = BoardSize(value = 10),
        height = BoardSize(value = 10),
        mineCount = PositiveNumber(value = 10),
    )

    ResultView.printCurrentMinesweeperBoard(
        boardView = minesweeperBoard.convertToView(),
    )
}
