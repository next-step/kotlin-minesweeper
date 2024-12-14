package minesweeper.view.input

import minesweeper.common.Col
import minesweeper.common.Row

object PointSelectView {
    fun parsePoint(): Pair<Row, Col> {
        print("open: ")

        val point = readln().split(",").map { it.trim().toInt() }

        return Pair(point[0], point[1])
    }
}
