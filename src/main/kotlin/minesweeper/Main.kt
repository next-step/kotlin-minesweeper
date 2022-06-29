package minesweeper

import minesweeper.domain.MineMap
import minesweeper.domain.MineSweeper.sweep
import minesweeper.ui.UserInput

fun main() {
    val height = UserInput.inputHeight().also { println() }
    val width = UserInput.inputWidth().also { println() }
    val mineCount = UserInput.inputMineCount().also { println() }

    val mineMap = MineMap(height, width, mineCount)
    val sweptMineMap = mineMap.sweep()

    println("지뢰찾기 게임 시작")
    do {
        val positions = UserInput.inputOpenPosition()
        val result = sweptMineMap.open(positions)
        if (!result) {
            println("Lose Game.")
            break
        }
        UserInput.printResult(sweptMineMap.map())
    } while (result)
}
