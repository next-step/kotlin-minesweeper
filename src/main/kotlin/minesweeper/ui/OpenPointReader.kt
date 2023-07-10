package minesweeper.ui

import minesweeper.domain.point.Point

object OpenPointReader {
    fun read(): Point {
        print("open: ")
        val (x, y) = readln().split(",")
            .map { it.trim() }
            .map { it.toInt() }
        return Point(x, y)
    }
}
