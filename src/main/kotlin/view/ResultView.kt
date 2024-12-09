package view

import map.Map

object ResultView {
    fun gameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMap(map: Map) {
        map.grid.points.rows.forEach { rows ->
            rows.columns.forEach { print(it.element.value + " ") }
            println()
        }
    }
}
