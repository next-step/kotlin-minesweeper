package minesweeper.domain

data class Position(val x: Int, val y: Int) {
    fun insideOf(width: Int, height: Int): Boolean = !(x > width - 1 || y > height - 1 || x < 0 || y < 0)
}
