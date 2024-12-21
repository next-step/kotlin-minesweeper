package mine.controller

import mine.domain.Minesweeper
import mine.view.InputView
import mine.view.OutputView

class MineController {
    fun gamsStart() {
        val mine = createMine()
        minesweeperStart(mine)
    }

    private fun createMine(): Minesweeper {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()
        return Minesweeper(height, width, mineCount)
    }

    private fun minesweeperStart(minesweeper: Minesweeper) {
        OutputView.gameStart(minesweeper)
    }
}
