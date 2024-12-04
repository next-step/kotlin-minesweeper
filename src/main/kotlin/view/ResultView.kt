package view

import map.Grid

object ResultView {
    fun gameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMap(grid: Grid) {
        grid.points.forEach { rows ->
            rows.forEach { print(it.element.value + " ") }
            println()
        }
    }
}
