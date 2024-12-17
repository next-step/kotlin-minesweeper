package view

import element.Element
import element.showable.Show
import map.Map
import map.Point

object ResultView {
    fun gameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printMap(map: Map) {
        map.grid.rows.columns.forEach { rows ->
            rows.points.forEach { print(it.value() + " ") }
            println()
        }
    }

    private fun Point.value(): String =
        this.element.value
            .takeIf { it != null && this.element.isShow() }
            ?: "C"

    private fun Element.isShow() = this.status.visibility is Show

    fun printLose() {
        println("Lose Game.")
    }
}
