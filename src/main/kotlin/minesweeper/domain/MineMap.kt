package minesweeper.domain

class MineMap(val height: Height, val width: Width) {

    fun height(): Int {
        return height.value
    }

    fun width(): Int {
        return width.value
    }
}
