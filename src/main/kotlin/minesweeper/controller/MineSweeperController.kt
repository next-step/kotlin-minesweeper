package minesweeper.controller

import minesweeper.domain.Cols
import minesweeper.domain.MineValue
import minesweeper.domain.Minesweeper
import minesweeper.domain.Rows
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
        val minesweeper = Minesweeper.from(Rows(rows), Cols(cols), MineValue(mineCount, rows, cols))

        printStart()

        while (true) {
            if (isGameWin(minesweeper, mineCount)) break

            val openPosition = enterOpenPosition()

            if (isGameLose(minesweeper, openPosition)) break

            minesweeper.open(openPosition)
            printResult(minesweeper.positions)
        }
    }

    private fun isGameWin(minesweeper: Minesweeper, mineCount: Int): Boolean {
        if (minesweeper.isGameWin(mineCount)) {
            printWin()
            return true
        }
        return false
    }

    private fun isGameLose(minesweeper: Minesweeper, openPosition: String): Boolean {
        if (minesweeper.isMinePosition(openPosition)) {
            printLose()
            return true
        }
        return false
    }
}
