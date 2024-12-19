package mine.controller

import mine.domain.Mine
import mine.view.InputView
import mine.view.OutputView

class MineController {
    fun gamsStart() {
        val mine = createMine()
        minesweeperStart(mine)
    }

    private fun createMine(): Mine {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()
        return Mine(height, width, mineCount)
    }

    private fun minesweeperStart(mine: Mine) {
        OutputView.gameStart(mine)
    }
}
