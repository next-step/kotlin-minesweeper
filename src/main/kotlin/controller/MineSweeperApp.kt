package controller

import model.Board
import model.BoardSize
import model.LengthOfSide
import model.NumberOfMine
import view.InputView
import view.ResultView

fun main() {
    val (row, col) = requestRowAndCol()
    val boardSize = BoardSize(row, col)
    val mineCount = NumberOfMine(
        number = InputView.requestInputByMode(InputView.Mode.MINE_COUNT),
        boardSize = boardSize
    )

    val board = Board(boardSize).apply {
        make(mineCount.getMineIndexes())
    }

    ResultView.printBoard(board, row)
}

private fun requestRowAndCol() =
    LengthOfSide(InputView.requestInputByMode(InputView.Mode.ROW)) to
        LengthOfSide(InputView.requestInputByMode(InputView.Mode.COL))
