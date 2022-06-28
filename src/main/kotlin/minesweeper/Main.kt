package minesweeper

import minesweeper.domain.MineMap
import minesweeper.domain.MineSweeper
import minesweeper.ui.UserInput

fun main() {
    val height = UserInput.inputHeight().also { println() }
    val width = UserInput.inputWidth().also { println() }
    val mineCount = UserInput.inputMineCount().also { println() }

    val mineMap = MineMap(height, width, mineCount)
    val sweptMineMap = MineSweeper.sweep(mineMap.map())
    UserInput.printResult(sweptMineMap)
}
