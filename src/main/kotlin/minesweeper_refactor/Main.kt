package minesweeper_refactor

import minesweeper_refactor.convert.convertToView
import minesweeper_refactor.domain.board.builder.minesweeperBoard
import minesweeper_refactor.domain.coordinate.Coordinate
import minesweeper_refactor.domain.game.GameEvent
import minesweeper_refactor.domain.game.MinesweeperGame
import minesweeper_refactor.view.InputView
import minesweeper_refactor.view.ResultView

fun main() {
    val minesweeperBoard = minesweeperBoard {
        boardSize {
            width(value = InputView.readWidth())
            height(value = InputView.readHeight())
        }

        mineCount(value = InputView.readMineCount())
    }

    val minesweeperGame = MinesweeperGame(minesweeperBoard = minesweeperBoard)

    ResultView.printGameStartMessage()

    val matchState = minesweeperGame.start(
        gameEvent = GameEvent(
            openCoordinateEvent = {
                val openCoordinate = InputView.readOpenCoordinate()
                Coordinate(x = openCoordinate.x, y = openCoordinate.y)
            },
            currentBoardEvent = { ResultView.printCurrentMinesweeperBoard(boardView = it.convertToView()) },
        ),
    )

    ResultView.printMatchState(matchStateView = matchState.convertToView())
}
