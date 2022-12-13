package minesweeper.io

import minesweeper.domain.Map

class Output {
    fun printMap(map: Map) {
        println(map.print())
    }
}