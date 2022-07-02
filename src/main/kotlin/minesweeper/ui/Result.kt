package minesweeper.ui

import minesweeper.entity.MapElement

object Result {
    fun informPlay(mineMap: List<List<MapElement>>) {
        println("지뢰찾기 게임 시작")
        mineMap.forEach { row: List<MapElement> -> println(row.joinToString(" ") { element: MapElement -> element.represent }) }
    }
}
