package minesweeper.controller

import minesweeper.domain.MineBoardFactory
import minesweeper.domain.strategy.RandomMineDeployStrategy
import minesweeper.dto.MineBoardMatrix
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeper(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun execute() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val mineBoard = MineBoardFactory.create(
            height = height,
            width = width,
            mineCount = mineCount,
            strategy = RandomMineDeployStrategy
        )

        outputView.printMineBoard(MineBoardMatrix.from(mineBoard = mineBoard, width = width))
    }
}
