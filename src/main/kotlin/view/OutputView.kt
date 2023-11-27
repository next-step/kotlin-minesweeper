package view

import domain.MineMap
import domain.field.Point

object OutputView {

    fun outputGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMineMap(map: MineMap) {
        val height = map.getHeight()
        repeat(height) { h ->
            printMineMapLine(map, h)
        }
    }

    private fun printMineMapLine(map: MineMap, line: Int) {
        val width = map.getWidth()
        repeat(width) { i ->
            print(map.resultMineStatus(Point(line, i)))
        }
        println()
    }
}
