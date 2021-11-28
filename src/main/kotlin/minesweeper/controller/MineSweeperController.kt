package minesweeper.controller

import minesweeper.domain.BoardFactory
import minesweeper.domain.DefaultRandomGenerator
import minesweeper.view.BoardDto
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperController {

    private val boardFactory = BoardFactory(DefaultRandomGenerator)

    fun start() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()

        val board = boardFactory.createBy(height = height, width = width, mineCount = mineCount)

        OutputView.printStart(BoardDto(board))
    }
}
