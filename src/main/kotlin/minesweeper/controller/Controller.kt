package minesweeper.controller

import minesweeper.domain.model.Board
import minesweeper.domain.model.Height
import minesweeper.domain.model.MineCount
import minesweeper.domain.model.Width
import minesweeper.view.InputView
import minesweeper.view.ResultView

object Controller {
    fun start() {
        val height = Height.from(InputView.getHeight())
        val width = Width.from(InputView.getWidth())
        val mineCount = MineCount.from(InputView.getMineCount())
        val board = Board.create(width, height, mineCount)
        ResultView.drawBoard(board)
    }
}
