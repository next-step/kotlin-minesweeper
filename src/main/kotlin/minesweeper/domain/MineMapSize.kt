package minesweeper.domain

class MineMapSize(private val width: Width, private val height: Height) {
    fun size(): Int {
        return width.value * height.value
    }

    fun width(): Int {
        return width.value
    }

    fun height(): Int {
        return height.value
    }

    fun getIndex(heightIndex: Int, widthIndex: Int): Int {
        return (heightIndex - 1) * width() + (widthIndex - 1)
    }
}
