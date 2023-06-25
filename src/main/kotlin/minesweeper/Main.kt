package minesweeper

import minesweeper.convert.convertToView
import minesweeper.domain.BoardSize
import minesweeper.domain.Coordinate
import minesweeper.domain.MinesweeperBoardGenerator
import minesweeper.domain.PositiveNumber
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val minesweeperBoard = MinesweeperBoardGenerator.generate(
        boardSize = BoardSize(
            width = InputView.readWidth(),
            height = InputView.readHeight(),
        ),

        mineCount = PositiveNumber(value = InputView.readMineCount()),
    )

    ResultView.printGameStartMessage()

    val matchState = minesweeperBoard.start(
        openCoordinateEvent = {
            val openCoordinate = InputView.readOpenCoordinate()
            Coordinate(x = openCoordinate.x, y = openCoordinate.y)
        },

        currentBoardEvent = { ResultView.printCurrentMinesweeperBoard(boardView = it.convertToView()) },
    )
}
