package minesweeper.controller

import minesweeper.domain.Minesweeper
import minesweeper.view.enterCols
import minesweeper.view.enterMineCount
import minesweeper.view.enterRows
import minesweeper.view.printResult
import minesweeper.view.printStart

class MineSweeperController {
    fun start() {
        val cols = enterCols()
        val rows = enterRows()
        val mineCount = enterMineCount()

        printStart()
        val minesweeper = Minesweeper(rows, cols, mineCount)
        printResult(minesweeper.minesweeperArray)
    }
}
