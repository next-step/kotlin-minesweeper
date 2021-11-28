package minesweeper.controller

import minesweeper.model.Board
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController {

    private val inputView = InputView()
    private val outputView = OutputView()

    fun play() {
        val height = inputView.getHeight()
        val width = inputView.getWidth()
        val mineCount = inputView.getMineCount()

        val board = Board.create(width, height, mineCount)
        outputView.showGamePlay(board)
    }
}
