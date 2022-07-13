package minesweeper.controller

import minesweeper.domain.MineBoard
import minesweeper.dto.MineBoardLength
import minesweeper.dto.MineBoardMatrix
import minesweeper.dto.MineCount
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeper(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun execute() {
        val height = MineBoardLength(inputView.inputHeight())
        val width = MineBoardLength(inputView.inputWidth())
        val mineCount = MineCount(inputView.inputMineCount())

        val mineBoard = MineBoard.create(
            height = height,
            width = width,
            mineCount = mineCount,
        )

        outputView.printStartGame()

        while (mineBoard.remainHiddenLands() && mineBoard.nonExistOpenedMine()) {
            mineBoard.open(inputView.inputCoordinate())
            outputView.printMineBoard(MineBoardMatrix.from(mineBoard = mineBoard, width = width))
        }

        outputView.printGameResult(mineBoard)
    }
}
