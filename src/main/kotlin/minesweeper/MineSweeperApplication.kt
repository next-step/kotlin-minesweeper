package minesweeper

import minesweeper.domain.CellFinder
import minesweeper.domain.HeightAndWidth
import minesweeper.domain.MineSweeperGame
import minesweeper.domain.Position
import minesweeper.domain.RandomPositionGenerator
import minesweeper.domain.Size
import minesweeper.ui.InputType
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val height = InputView.inputSize(InputType.HEIGHT)
    val width = InputView.inputSize(InputType.WIDTH)
    val mineCount = InputView.inputSize(InputType.COUNT)

    val minePositions = RandomPositionGenerator(height, width).generate(mineCount)
    val cellFinder = CellFinder.init(height, width)
    cellFinder.convert(minePositions)

    val mineSweeperGame = MineSweeperGame(cellFinder)
    play(mineSweeperGame, HeightAndWidth(height, width), mineCount)
}

private fun play(mineSweeperGame: MineSweeperGame, heightAndWidth: HeightAndWidth, mineCount: Size) {
    ResultView.printGameStartMessage()
    while (!mineSweeperGame.isFinished(mineCount)) {
        val position = InputView.inputOpenPosition()
        mineSweeperGame.open(position)
        printResult(mineSweeperGame, position, heightAndWidth)
        printWinResult(mineSweeperGame, mineCount)
    }
}

private fun printResult(mineSweeperGame: MineSweeperGame, position: Position, heightAndWidth: HeightAndWidth) {
    when (mineSweeperGame.isMine(position)) {
        true -> ResultView.printLoseGameMessage()
        false -> ResultView.printMines(mineSweeperGame, heightAndWidth)
    }
}

private fun printWinResult(mineSweeperGame: MineSweeperGame, mineCount: Size) {
    if (mineSweeperGame.isWin(mineCount)) {
        ResultView.printWinGameMessage()
    }
}
