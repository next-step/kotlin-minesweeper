package minesweeper

import minesweeper.model.MineBoard
import minesweeper.model.MineMap
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperApplication {
    fun play() {
        val height = InputView.readHeight()
        val width = InputView.readWidth()
        val mineCount = InputView.readMineCount()

        val map = MineMap.of(height, width)
        val mines = map.selectRandomMines(mineCount)
        val mineBoard = MineBoard(map, mines)

        OutputView.printMineBoard(mineBoard)
    }
}

fun main() {
    MineSweeperApplication().play()
}
