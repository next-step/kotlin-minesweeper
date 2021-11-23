package minesweeper.controller

import minesweeper.domain.realBoard.RealBoard
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController {

    fun start(inputView: InputView, resultView: ResultView) {
        val width = inputView.inputWidth()
        val height = inputView.inputHeight()
        val mine = inputView.inputMine()
        val realBoard = RealBoard.of(width, height, mine)
    }
}
