package minesweeper.controller

import minesweeper.domain.Position
import minesweeper.domain.RandomGeneratorStrategy
import minesweeper.domain.factory
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperBoardController {
    fun run() {
        val mineSweeperBoard = factory {
            height(InputView.inputHeight())
            width(InputView.inputWidth())
            countOfMine(InputView.inputCountOfMine())
            strategy(RandomGeneratorStrategy)
        }.also {
            OutputView.printInitMineSweeperBoard(it)
        }

        while (mineSweeperBoard.isPlaying) {
            try {
                val inputPosition = InputView.inputOpenPosition()
                mineSweeperBoard.openAt(inputPosition.toPosition())
                OutputView.printCurrentMineSweeperBoard(mineSweeperBoard)
            } catch (e: Exception) {
                println(e.message)
            }
        }

        OutputView.printResult(mineSweeperBoard.getResult())
    }
}

private fun Pair<Int, Int>.toPosition() = Position(this.first, this.second)
