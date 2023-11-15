package minesweeper.domain

class MineMap(private val height: Height, private val width: Width) {

    fun height(): Int {
        return height.value
    }

    fun width(): Int {
        return width.value
    }
}
