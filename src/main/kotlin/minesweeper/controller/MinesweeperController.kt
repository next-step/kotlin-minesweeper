package minesweeper.controller

import minesweeper.domain.Minefield
import minesweeper.domain.Minesweeper
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MinesweeperController {
    fun start() {
        val widthResult = InputView.inputWidth()
        val heightResult = InputView.inputHeight()
        val minefield = Minefield.of(widthResult, heightResult)
        val minesweeper = Minesweeper(minefield)

        val numberOfMinesResult = InputView.inputNumberOfMines()
        val minesBoard = minesweeper.enrollMines(numberOfMinesResult)

        ResultView.startGame(minesBoard)
    }
}
