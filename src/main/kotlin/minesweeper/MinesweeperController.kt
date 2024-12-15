package minesweeper

import minesweeper.application.GenerateMinesweeperCommand
import minesweeper.application.MinesweeperService
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class MinesweeperController(
    private val minesweeperService: MinesweeperService,
) {
    fun start() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()

        val board =
            minesweeperService.generateBoard(
                GenerateMinesweeperCommand(
                    height = height,
                    width = width,
                    mineCount = mineCount,
                ),
            )
        ResultView.render(board)
    }
}
