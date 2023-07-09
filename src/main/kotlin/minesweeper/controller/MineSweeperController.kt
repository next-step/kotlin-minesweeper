package minesweeper.controller

import minesweeper.domain.Minesweeper
import minesweeper.view.enterCols
import minesweeper.view.enterMineCount
import minesweeper.view.enterOpenPosition
import minesweeper.view.enterRows
import minesweeper.view.printLose
import minesweeper.view.printResult
import minesweeper.view.printStart
import minesweeper.view.printWin

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
