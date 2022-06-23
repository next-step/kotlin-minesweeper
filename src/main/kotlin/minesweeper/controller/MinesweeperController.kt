package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.parser.InputParser
import minesweeper.view.BoardView
import minesweeper.view.MinesweeperView

class MinesweeperController {

    fun start() {
        val height = getHeight()
        val width = getWidth()
        val minesCount = getMinesCount()

        val board = Board(height, width, minesCount)

        BoardView(board).print()
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
