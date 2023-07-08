package view

import domain.MineMap

object DisplayView {
    private const val SEPARATOR = " "

    fun displayMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.elements.forEach { row ->
            val line = row.joinToString(SEPARATOR) { element -> element.toString() }
            println(line)
        }
    }
}
