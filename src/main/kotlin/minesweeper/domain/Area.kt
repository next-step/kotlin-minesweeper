package minesweeper.domain

data class Area(val width: Int, val height: Int) {
    val size: Int = width * height
}
