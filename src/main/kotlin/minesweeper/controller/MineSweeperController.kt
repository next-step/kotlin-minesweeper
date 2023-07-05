package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.RandomCoordinatesGenerator
import minesweeper.view.MineSweeperInputView
import minesweeper.view.MineSweeperResultView

class MineSweeperController {
    fun run() {
        val height = MineSweeperInputView.inputHeight()
        val width = MineSweeperInputView.inputWidth()
        val numberOfMines = MineSweeperInputView.inputNumberOfMines()

        val mineCoordinates = RandomCoordinatesGenerator(height, width).create(numberOfMines)
        val board = Board.create(height, width, mineCoordinates)

        MineSweeperResultView.start()
        MineSweeperResultView.printBoard(board)
    }
}

fun main() {
    MineSweeperController().run()
}
