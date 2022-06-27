package minesweeper.ui

import minesweeper.domain.MineMap

object UserInput {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toIntOrNull() ?: throw IllegalArgumentException()
    }

    fun printResult(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        for(i in mineMap.map().indices) {
            for (j in mineMap.map()[i].indices) {
                if (mineMap.map()[i][j]) {
                    print("*")
                } else {
                    print("C")
                }
            }
            println()
        }
    }
}