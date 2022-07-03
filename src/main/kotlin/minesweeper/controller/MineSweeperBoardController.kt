package minesweeper.controller

import minesweeper.domain.factory
import minesweeper.domain.randomGenerateStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperBoardController {
    fun run() {
        factory {
            height(InputView.inputHeight())
            width(InputView.inputWidth())
            countOfMine(InputView.inputCountOfMine())
            strategy { width: Int, height: Int, countOfMine: Int -> randomGenerateStrategy(width, height, countOfMine) }
        }.also {
            OutputView.printMineSweeperBoard(it)
        }
    }
}
