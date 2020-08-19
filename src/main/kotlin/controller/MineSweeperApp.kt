package controller

import model.Board
import model.BoardSize
import model.LengthOfSide
import model.NumberOfMine
import view.InputView
import view.ResultView

fun main() {
    val (row, col) = requestLengthOfSide(InputView.Mode.ROW) to requestLengthOfSide(InputView.Mode.COL)

    val boardSize = BoardSize(row, col)
    val mineCount = requestMineCount(boardSize)

    val board = Board(boardSize, mineCount.getMineIndexes())

    ResultView.printBoard(board)
}

private fun requestMineCount(boardSize: BoardSize): NumberOfMine =
    runCatching {
        NumberOfMine(
            number = InputView.requestInputByMode(InputView.Mode.MINE_COUNT),
            boardSize = boardSize
        )
    }.also {
        showErrorIfFailure(it)
        return@also
    }.getOrNull()!!

private fun requestLengthOfSide(inputMode: InputView.Mode): LengthOfSide =
    runCatching { LengthOfSide(InputView.requestInputByMode(inputMode)) }
        .also {
            showErrorIfFailure(it)
            return@also
        }.getOrNull()!!

private fun <T> showErrorIfFailure(result: Result<T>) {
    if (result.isFailure) {
        ResultView.printError(result.exceptionOrNull())
    }
}
