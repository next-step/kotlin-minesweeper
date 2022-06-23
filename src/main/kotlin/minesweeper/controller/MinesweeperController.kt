package minesweeper.controller

import minesweeper.parser.InputParser
import minesweeper.view.MinesweeperView

class MinesweeperController {

    fun start() {
        val height = getHeight()
        val width = getWidth()
        val minesCount = getMinesCount()

        // debug
        println("height: $height, width: $width, minesCount: $minesCount")
    }

    private fun getHeight(): Int {
        MinesweeperView.printInputHeight()
        return InputParser.parseHeight(readln())
    }

    private fun getWidth(): Int {
        MinesweeperView.printInputWidth()
        return InputParser.parseWidth(readln())
    }

    private fun getMinesCount(): Int {
        MinesweeperView.printInputMineCount()
        return InputParser.parseMinesCount(readln())
    }
}
