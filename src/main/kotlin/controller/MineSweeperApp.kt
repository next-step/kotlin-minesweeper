package controller

import Result
import check
import model.Board
import model.BoardSize
import model.LengthOfSide
import model.NumberOfMine
import view.InputView
import view.ResultView

fun main() {
    val row = tryLengthOfSideInput(InputView.requestInputByMode(InputView.Mode.ROW)).check() ?: return
    val col = tryLengthOfSideInput(InputView.requestInputByMode(InputView.Mode.COL)).check() ?: return

    val boardSize = BoardSize(row, col)
    val mineCount = tryNumberOfMineInput(
        number = InputView.requestInputByMode(InputView.Mode.MINE_COUNT),
        boardSize = boardSize
    ).check() ?: return

    val board = Board(boardSize, mineCount.getMineIndexes())

    ResultView.printBoard(board, row)
}

private fun tryLengthOfSideInput(data: Int): Result<LengthOfSide> =
    try {
        Result.Success(LengthOfSide(data))
    } catch (e: IllegalArgumentException) {
        Result.Failure(e)
    }

private fun tryNumberOfMineInput(number: Int, boardSize: BoardSize): Result<NumberOfMine> =
    try {
        Result.Success(NumberOfMine(number, boardSize))
    } catch (e: IllegalArgumentException) {
        Result.Failure(e)
    }
