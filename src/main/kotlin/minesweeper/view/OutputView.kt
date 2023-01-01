package minesweeper.view

import minesweeper.domain.MineMap

object OutputView {

    private const val MESSAGE_START_GAME = "지뢰게임 시작"

    fun printHeight(height: Int) {
        println(height)
    }

    fun printWidth(width: Int) {
        println(width)
    }

    fun printMiceCount(mineCount: Int) {
        println(mineCount)
    }

    fun startGame(height: Int, width: Int, mineCount: Int) {
        println(MESSAGE_START_GAME)

        val mineMap = MineMap(height, width, mineCount).generate()

        mineMap.forEach {
            if ((it.position + 1) % width == 0) {
                print(it.toString() + "\n")
                return@forEach
            }

            print("$it")
        }
    }
}
