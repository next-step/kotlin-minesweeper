package controller

import enums.MineType
import factory.MinesweeperFactory
import view.InputView
import view.ResultView

class MinesweeperController(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val minesweeperFactory: MinesweeperFactory,
    private val mineType: MineType
) {

    fun start() {

        val minesweeperService = minesweeperFactory.getType(mineType)
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMine()

        val minesweeper = minesweeperService.initGame(height, width, mineCount)

        resultView.printGameStart()
        resultView.printMinesweeperMap(minesweeper.mineMap)
    }
}