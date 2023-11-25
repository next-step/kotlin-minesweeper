package view

import domain.MineMap

object OutputView {

    fun outputGameStart(map: MineMap) {
        println("지뢰찾기 게임 시작")
        printMineMap(map)
    }

    private fun printMineMap(map: MineMap) {
        val height = map.getHeight()
        repeat(height) { h ->
            printMineMapLine(map, h)
        }
    }

    private fun printMineMapLine(map: MineMap, line: Int) {
        val width = map.getWidth()
        repeat(width) { i ->
            print(map.resultMineStatus(line, i))
        }
        println()
    }
}
