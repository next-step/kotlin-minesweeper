package controller

import service.MinesweeperService
import view.InputView
import view.ResultView

class MinesweeperController(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val minesweeperService: MinesweeperService
) {

    fun start() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMine()

        val minesweeper = minesweeperService.initGame(height, width, mineCount)

        resultView.printGameStart()
        resultView.printMinesweeperMap(minesweeper.mineMap)
    }
}