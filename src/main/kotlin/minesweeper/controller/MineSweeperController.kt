package minesweeper.controller

import minesweeper.domain.Dimension
import minesweeper.domain.Grid
import minesweeper.domain.MineCount
import minesweeper.domain.RandomMineGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView

object MineSweeperController {
    fun start() {
        val height = InputView.readHeight()
        val width = InputView.readWidth()
        val mineCount = InputView.readMineCount()
        val grid =
            Grid(
                dimension = Dimension(height, width),
                mineCount = MineCount(mineCount),
                mineGenerator = RandomMineGenerator()
            )
        ResultView.renderBoard(grid)
    }
}
