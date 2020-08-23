package controller

import model.Board
import model.BoardSize
import model.Coordinates
import model.Gamer
import model.LengthOfSide
import model.NumberOfMine
import model.Winner
import view.InputView
import view.ResultView

fun main() {
    registerGamer().also {
        ResultView.printStartGame()
        startGameUntilTheEnd(it)
        ResultView.printResult(it)
    }
}

private fun startGameUntilTheEnd(gamer: Gamer) {
    while (!Winner.isLose(gamer)) {
        val coordinates = InputView.requestCoordinates()
        gamer.clickCoordinate(Coordinates(coordinates[0], coordinates[1]))
        ResultView.printBoard(gamer)
    }
}

private fun registerGamer(): Gamer {
    val row = requestLengthOfSideUntilNonNull(InputView.Mode.ROW)
    val col = requestLengthOfSideUntilNonNull(InputView.Mode.COL)
    val boardSize = BoardSize(row, col)
    val mineCount = requestNumberOfMineUntilNonNull(boardSize)

    val board = Board(boardSize, mineCount.getMineIndexes())
    return Gamer(board)
}

private fun requestNumberOfMineUntilNonNull(boardSize: BoardSize): NumberOfMine {
    var result = requestMineCount(boardSize)
    while (result == null) {
        result = requestMineCount(boardSize)
    }
    return result
}

private fun requestLengthOfSideUntilNonNull(inputMode: InputView.Mode): LengthOfSide {
    var result = requestLengthOfSide(inputMode)
    while (result == null) {
        result = requestLengthOfSide(inputMode)
    }
    return result
}

private fun requestMineCount(boardSize: BoardSize): NumberOfMine? =
    runCatching {
        NumberOfMine(
            number = InputView.requestInputByMode(InputView.Mode.MINE_COUNT),
            boardSize = boardSize
        )
    }
        .onFailure { ResultView.printError(it) }
        .getOrNull()

private fun requestLengthOfSide(inputMode: InputView.Mode): LengthOfSide? =
    runCatching { LengthOfSide(InputView.requestInputByMode(inputMode)) }
        .onFailure { ResultView.printError(it) }
        .getOrNull()
