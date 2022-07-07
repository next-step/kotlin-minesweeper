package minesweeper.controller

import minesweeper.domain.MineGeneratorStrategy
import minesweeper.domain.factory
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperBoardController {
    fun run() {
        factory {
            height(InputView.inputHeight())
            width(InputView.inputWidth())
            countOfMine(InputView.inputCountOfMine())
            strategy(MineGeneratorStrategy())
        }.also {
            OutputView.printAllOpenMineSweeperBoard(it)
        }
    }
}
