package minesweeper.view.input

object PointSelectView {
    fun parsePoint(): Pair<Int, Int> {
        print("open: ")

        val point = readln().split(",").map { it.trim().toInt() }

        return Pair(point[0], point[1])
    }
}
