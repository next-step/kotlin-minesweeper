package minesweeper

import minesweeper.domain.BlockCreator
import minesweeper.domain.Blocks
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class Controller(private val inputView: InputView, private val resultView: ResultView) {
    fun drawMineSweeper() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputLandMine()

        val blockCreator = BlockCreator(width, height, mineCount)
        val blocks = Blocks(width, height, blockCreator.blocks)

        resultView.drawBlocks(blocks)
    }
}
