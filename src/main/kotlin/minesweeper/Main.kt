package minesweeper

import minesweeper.domain.MineMap
import minesweeper.ui.UserInput

fun main() {
    val height = UserInput.inputHeight().also { println() }
    val width = UserInput.inputWidth().also { println() }
    val mineCount = UserInput.inputMineCount().also { println() }

    val mineMap = MineMap.build(height, width)
    UserInput.printResult(mineMap)
}