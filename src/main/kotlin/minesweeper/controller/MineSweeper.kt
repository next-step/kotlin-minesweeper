package minesweeper.controller

import minesweeper.domain.MineBoard
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

        val mineBoard = MineBoard.createWithRandomStrategy(
            height = height,
            width = width,
            mineCount = mineCount,
        )

        outputView.printMineBoard(MineBoardMatrix.from(mineBoard = mineBoard, width = width))
    }
}
