package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.inputHeight()
    val width = inputView.inputWidth()
    val mineCount = inputView.inputLandMine()

    val blockCreator = BlockCreator(height, width, mineCount)
    val blocks = Blocks(blockCreator.normalBlocks, blockCreator.mineBlocks)
    resultView.printBlocks(blocks.blocks.chunked(width))
}
