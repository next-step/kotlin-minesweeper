package minesweeper.controller

import minesweeper.domain.*
import minesweeper.view.BoardDto
import minesweeper.view.InputView
import minesweeper.view.OutputView

object MineSweeperController {

    private val boardFactory = BoardFactory(DefaultRandomGenerator)

    fun start() {
        val height = Height(InputView.getHeight())
        val width = Width(InputView.getWidth())
        val mineCount = MineCount(InputView.getMineCount())

        val board = boardFactory.createBy(height, width, mineCount)

        OutputView.printStart(BoardDto(board))
    }
}
