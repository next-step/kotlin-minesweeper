package mine.controller

import mine.domain.Minesweeper
import mine.view.InputView
import mine.view.OutputView

class MineController {
    fun gamsStart() {
        val mine = createMine()
        gameProgress(mine)
    }

    private fun gameProgress(mine: Minesweeper) {
        while (true) {
            val coordinate = InputView.gameStart()
            val isMineCell = mine.mineBoard.isMine(coordinate)
            val isAllMinedOpen = mine.areAllSafeCellsOpened()
            when {
                isMineCell -> {
                    OutputView.gameLoseEnd()
                    return
                }

                isAllMinedOpen -> {
                    OutputView.gameWinEnd()
                    return
                }

                else -> {
                    mine.openCells(coordinate)
                    OutputView.gameResult(mine.mineBoard)
                }
            }
        }
    }

    private fun createMine(): Minesweeper {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()
        return Minesweeper(height, width, mineCount)
    }
}
