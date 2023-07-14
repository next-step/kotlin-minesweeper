package minesweeper2.controller

import minesweeper2.domain.Minesweeper
import minesweeper2.view.enterCols
import minesweeper2.view.enterMineCount
import minesweeper2.view.enterOpenPosition
import minesweeper2.view.enterRows
import minesweeper2.view.printLose
import minesweeper2.view.printResult
import minesweeper2.view.printStart
import minesweeper2.view.printWin

class MineSweeperController {
    fun start() {
        val cols = enterCols()
        val rows = enterRows()
        val mineCount = enterMineCount()
        val minesweeper = Minesweeper.from(rows, cols, mineCount)

        printStart()

        var openPosition = enterOpenPosition()
        while (minesweeper.canPlayGame(openPosition)) {
            minesweeper.open(openPosition)
            printResult(minesweeper.positions)
            openPosition = enterOpenPosition()
        }

        printGameResult(minesweeper)
    }

    private fun printGameResult(minesweeper: Minesweeper) {
        if (minesweeper.isGameWin()) {
            printWin()
            return
        }
        printLose()
    }
}
