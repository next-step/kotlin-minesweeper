package minesweeper

data class Location(val x: Int, val y: Int) {
    val location: Pair<Int, Int> = Pair(x, y)
}
