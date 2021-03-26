package minesweeper.view

import minesweeper.inputdata.MineGameCondition

object InputView {
    private tailrec fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toIntOrNull() ?: inputHeight()
    }

    private tailrec fun inputWidth(): Int {
        println("\n너비를 입력하세요.")
        return readLine()?.toIntOrNull() ?: inputWidth()
    }

    private tailrec fun inputMineCount(): Int {
        println("\n지뢰는 몇 개인가요?")
        return readLine()?.toIntOrNull() ?: inputMineCount()
    }

    fun inputMineGameCondition(): MineGameCondition {
        val mapHeight = inputHeight()
        val mapWidth = inputWidth()
        val mapMineCount = inputMineCount()
        return MineGameCondition.of(mapHeight, mapWidth, mapMineCount)
    }
}
