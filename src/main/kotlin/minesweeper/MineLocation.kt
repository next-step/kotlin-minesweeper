package minesweeper

data class MineLocation(val x: Int, val y: Int) {
    val location: Pair<Int, Int> = Pair(x, y)
}
