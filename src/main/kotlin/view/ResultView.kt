package view

import enums.MinesweeperShape

object ResultView {

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMinesweeperMap(mineMap: List<MutableList<MinesweeperShape>>) {
        mineMap.forEach { row ->
            row.forEach { columns ->
                print("${columns.printShape} ")
            }
            println()
        }
    }
}
