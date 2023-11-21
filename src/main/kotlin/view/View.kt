package view

import domain.MineMap

object View {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }

    fun outputGameStart(map: MineMap) {
        println("지뢰찾기 게임 시작")
        printMineMap(map)
    }

    private fun printMineMap(map: MineMap) {
        val height = map.mapInfo.height
        repeat(height) { h ->
            printMineMapLine(map, h)
        }
    }

    private fun printMineMapLine(map: MineMap, line: Int) {
        val width = map.mapInfo.width
        repeat(width) { i ->
            print(map.isMineOn(i, line))
        }
        println()
    }
}
