package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.inputHeight()
    val width = inputView.inputWidth()
    val mineCount = inputView.inputLandMine()

    val blockRepository = BlockRepository(height, width, mineCount)
    resultView.printBlocks(blockRepository.blocks)
}