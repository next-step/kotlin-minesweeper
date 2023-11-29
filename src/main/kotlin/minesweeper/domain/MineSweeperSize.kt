package minesweeper.domain

class MineSweeperSize(val width: Int, val height: Int) {
    fun getArea() = width * height
}
